package com.weaver.police.controller;

import com.alibaba.fastjson.JSONObject;
import com.weaver.police.bean.*;
import com.weaver.police.constant.PoliceConstant;
import com.weaver.police.service.SendService;
import com.weaver.police.util.EncryptUtil;
import com.weaver.police.util.JedisUtil;
import com.weaver.police.util.RestResponse;
import com.weaver.police.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Author      :wyl
 * @Date        :2019/4/16  16:04
 * @Version 1.0 :
 * @Description :
 **/
@RestController
@RequestMapping("/api/securtity")
public class SecurtityAction {

    @Value("${security.key}")
    private String key;

    @Autowired
    private SendService sendService;

    private static EncryptUtil encryptUtil = EncryptUtil.getInstance();


    @RequestMapping(value = "/addOperateLog", method = RequestMethod.POST)
    public RestResponse addOperateLog(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam(value = "USER_INFO") String USER_INFO,
                                      @RequestParam(value = "OPERATE_TIME") String OPERATE_TIME,
                                      @RequestParam(value = "OPERATE_TYPE") String OPERATE_TYPE,
                                      @RequestParam(value = "OPERATE_RESULT") String OPERATE_RESULT){
        //可选条件
        String TERMINAL_ID = StringUtils.isBlank(request.getParameter("TERMINAL_ID")) ? Util.getIpAddr(request) : request.getParameter("TERMINAL_ID");
        String ORGANIZATION_ID =Util.null2String(request.getParameter("ORGANIZATION_ID"));
        String ERROR_CODE =Util.null2String(request.getParameter("ERROR_CODE"));
        String OPERATE_NAME =Util.null2String(request.getParameter("OPERATE_NAME"));
        String OPERATE_CONDITION =Util.null2String(request.getParameter("OPERATE_CONDITION"));
        String OPERATE_NUMBER =Util.null2String(request.getParameter("OPERATE_NUMBER"));
        String OPERATE_TABLE =Util.null2String(request.getParameter("OPERATE_TABLE"));
        String OPERATE_KEY =Util.null2String(request.getParameter("OPERATE_KEY"));


        TERMINAL_ID = encryptUtil.Base64Decode(TERMINAL_ID);
        USER_INFO = encryptUtil.Base64Decode(USER_INFO);
        OPERATE_TIME = encryptUtil.Base64Decode(OPERATE_TIME);
        OPERATE_TYPE = encryptUtil.Base64Decode(OPERATE_TYPE);
        OPERATE_RESULT = encryptUtil.Base64Decode(OPERATE_RESULT);

        ORGANIZATION_ID = encryptUtil.Base64Decode(ORGANIZATION_ID);
        ERROR_CODE = encryptUtil.Base64Decode(ERROR_CODE);
        OPERATE_NAME = encryptUtil.Base64Decode(OPERATE_NAME);
        OPERATE_CONDITION = encryptUtil.Base64Decode(OPERATE_CONDITION);
        OPERATE_NUMBER = encryptUtil.Base64Decode(OPERATE_NUMBER);
        OPERATE_TABLE = encryptUtil.Base64Decode(OPERATE_TABLE);
        OPERATE_KEY = encryptUtil.Base64Decode(OPERATE_KEY);

        UserInfo userInfo = JSONObject.parseObject(USER_INFO,UserInfo.class);
        String userId = StringUtils.isBlank(userInfo.getCertificatenum()) ? userInfo.getId() : userInfo.getCertificatenum();
        //添加必选属性
        OperateLog operateLog = new OperateLog(
                Util.get32UUID(),
                PoliceConstant.REG_ID,
                Util.null2String(userId,"none"),
                Util.null2String(userInfo.getDepartmentname(),"none"),
                Util.null2String(userInfo.getLastname(),"none"),
                TERMINAL_ID,
                Util.getNowDateTimeStr(OPERATE_TIME),
                Util.getMethod(OPERATE_TYPE),
                OPERATE_RESULT);
        //添加可选属性
        operateLog.setORGANIZATION_ID(ORGANIZATION_ID)
                .setERROR_CODE(ERROR_CODE)
                .setOPERATE_NAME(OPERATE_NAME)
                .setOPERATE_CONDITION(OPERATE_CONDITION)
                .setOPERATE_NUMBER(Util.getInt(OPERATE_NUMBER))
                .setOPERATE_TABLE(OPERATE_TABLE)
                .setOPERATE_KEY(OPERATE_KEY);

        String status = sendService.setMessage(PoliceConstant.OPERATE_LOG_PREFIX,JSONObject.toJSONString(operateLog));

        return RestResponse.result(status);
    }

    /**
     * 接口服务日志的调用
     * @param request
     * @param data
     * @return
     */
    @PostMapping(value = "/addInterfaceLog")
    public RestResponse addInterfaceLog(HttpServletRequest request,
                                        @RequestBody String data){
        //解密传过来的参数
        data = encryptUtil.Base64Decode(data);

        String status = null;
        try {
            EMInterfaceLogBean emInterfaceLogBean = JSONObject.parseObject(data,EMInterfaceLogBean.class);
            UserInfo userInfo = JSONObject.parseObject(emInterfaceLogBean.getUserInfo(),UserInfo.class);

            String userId = StringUtils.isBlank(userInfo.getCertificatenum()) ? userInfo.getId() : userInfo.getCertificatenum();
            InterfaceLog interfaceLog = new InterfaceLog(
                    Util.get32UUID(),
                    PoliceConstant.REG_ID,
                    emInterfaceLogBean.getTERMINAL_ID(),
                    Util.getNowDateTimeStr(emInterfaceLogBean.getINTERFACE_TIME()),
                    emInterfaceLogBean.getREQUESTER(),
                    emInterfaceLogBean.getINTERFACE_RESULT(),
                    emInterfaceLogBean.getINTERFACE_CONDITION());
            //设置可选条件
            interfaceLog.setUSER_ID(Util.null2String(userId,"none"))
                    .setORGANIZATION(userInfo.getDepartmentname())
                    .setUSER_NAME(userInfo.getLastname());

            status = sendService.setMessage(PoliceConstant.INTERFACE_LOG_PREFIX,JSONObject.toJSONString(interfaceLog));
            return RestResponse.result(status);
        } catch (Exception e) {
            return RestResponse.error(500,"JSON转换失败");
        }


    }

    /**
     *批量插入数据
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/addListLog", method = RequestMethod.POST)
    public RestResponse addListOperateLog(HttpServletRequest request, HttpServletResponse response,
                                          @RequestParam(value = "data") String data){
        String status = PoliceConstant.OK;

        data = encryptUtil.Base64Decode(data);

        try{
            DataLogBean dataLogBean = JSONObject.parseObject(data,DataLogBean.class);
            UserInfo userInfo = JSONObject.parseObject(dataLogBean.getUserInfo(),UserInfo.class);

            List<OperateLog> operateLogs = JSONObject.parseArray(dataLogBean.getOperateLog(),OperateLog.class);
            List<InterfaceLog> interfaceLogs = JSONObject.parseArray(dataLogBean.getInterfaceLog(),InterfaceLog.class);
            String userId = StringUtils.isBlank(userInfo.getCertificatenum()) ? userInfo.getId() : userInfo.getCertificatenum();

            for(OperateLog operateLog : operateLogs){
                String OPERATE_TIME = operateLog.getOPERATE_TIME();
                operateLog.setNUM_ID(Util.get32UUID())
                        .setREG_ID(PoliceConstant.REG_ID)
                        .setUSER_ID(Util.null2String(userId,"none"))
                        .setORGANIZATION(Util.null2String(userInfo.getDepartmentname(),"none"))
                        .setUSER_NAME(Util.null2String(userInfo.getLastname(),"none"))
                        .setOPERATE_TIME(Util.getNowDateTimeStr(OPERATE_TIME));
                status = sendService.setMessage(PoliceConstant.OPERATE_LOG_PREFIX,JSONObject.toJSONString(operateLog));
                if(!StringUtils.equalsIgnoreCase(PoliceConstant.OK,status)){
                    status = PoliceConstant.FAIL;
                }
            }

            for(InterfaceLog interfaceLog : interfaceLogs){
                String TERMINAL_ID = Util.null2String(interfaceLog.getTERMINAL_ID(),"none");
                String INTERFACE_TIME = interfaceLog.getINTERFACE_TIME();
                interfaceLog.setNUM_ID(Util.get32UUID())
                        .setREG_ID(PoliceConstant.REG_ID)
                        .setTERMINAL_ID(TERMINAL_ID)
                        .setINTERFACE_TIME(Util.getNowDateTimeStr(INTERFACE_TIME))
                        .setUSER_ID(Util.null2String(userId,"none"));
                status = sendService.setMessage(PoliceConstant.INTERFACE_LOG_PREFIX,JSONObject.toJSONString(interfaceLog));
                if(!StringUtils.equalsIgnoreCase(PoliceConstant.OK,status)){
                    status = PoliceConstant.FAIL;
                }

            }
            return RestResponse.result(status);
        }catch (Exception e){
            return RestResponse.error(500,"JSON转换失败");
        }


    }



}
