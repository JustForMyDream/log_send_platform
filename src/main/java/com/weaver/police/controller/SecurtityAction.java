package com.weaver.police.controller;

import com.alibaba.fastjson.JSONObject;
import com.weaver.police.bean.InterfaceLog;
import com.weaver.police.bean.OperateLog;
import com.weaver.police.bean.UserInfo;
import com.weaver.police.constant.PoliceConstant;
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
    private JedisUtil jedisUtil;

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


        TERMINAL_ID = encryptUtil.DESdecode(TERMINAL_ID,key);
        USER_INFO = encryptUtil.DESdecode(USER_INFO,key);
        OPERATE_TIME = encryptUtil.DESdecode(OPERATE_TIME,key);
        OPERATE_TYPE = encryptUtil.DESdecode(OPERATE_TYPE,key);
        OPERATE_RESULT = encryptUtil.DESdecode(OPERATE_RESULT,key);

        ORGANIZATION_ID = encryptUtil.DESdecode(ORGANIZATION_ID,key);
        ERROR_CODE = encryptUtil.DESdecode(ERROR_CODE,key);
        OPERATE_NAME = encryptUtil.DESdecode(OPERATE_NAME,key);
        OPERATE_CONDITION = encryptUtil.DESdecode(OPERATE_CONDITION,key);
        OPERATE_NUMBER = encryptUtil.DESdecode(OPERATE_NUMBER,key);
        OPERATE_TABLE = encryptUtil.DESdecode(OPERATE_TABLE,key);
        OPERATE_KEY = encryptUtil.DESdecode(OPERATE_KEY,key);

        UserInfo userInfo = JSONObject.parseObject(USER_INFO,UserInfo.class);
        //添加必选属性
        OperateLog operateLog = new OperateLog(
                Util.get32UUID(),
                PoliceConstant.REG_ID,
                userInfo.getId(),
                userInfo.getDepartmentname(),
                userInfo.getLastname(),
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

       String status = jedisUtil.setPreFix(PoliceConstant.OPERATE_LOG_PREFIX,Util.get16UUID(),JSONObject.toJSONString(operateLog));
       return RestResponse.result(status);
    }



    @RequestMapping(value = "/addInterfaceLog", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse addInterfaceLog(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "REQUESTER") String REQUESTER,
                                  @RequestParam(value = "INTERFACE_TIME") String INTERFACE_TIME,
                                  @RequestParam(value = "INTERFACE_RESULT") String INTERFACE_RESULT,
                                  @RequestParam(value = "INTERFACE_CONDITION") String INTERFACE_CONDITION){
        String TERMINAL_ID = StringUtils.isBlank(request.getParameter("TERMINAL_ID")) ? Util.getIpAddr(request) : request.getParameter("TERMINAL_ID");
        //可选条件
        String USER_INFO =Util.null2String(request.getParameter("USER_INFO"));
        String INTERFACE_NAME =Util.null2String(request.getParameter("INTERFACE_NAME"));
        String ORGANIZATION_ID =Util.null2String(request.getParameter("ORGANIZATION_ID"));
        String ERROR_CODE =Util.null2String(request.getParameter("ERROR_CODE"));
        String INTERFACE_NUMBER =Util.null2String(request.getParameter("INTERFACE_NUMBER"));
        String INTERFACE_TABLE =Util.null2String(request.getParameter("INTERFACE_TABLE"));
        String INTERFACE_KEY =Util.null2String(request.getParameter("INTERFACE_KEY"));

        //解密
        TERMINAL_ID = encryptUtil.DESdecode(TERMINAL_ID,key);
        USER_INFO = encryptUtil.DESdecode(USER_INFO,key);
        REQUESTER = encryptUtil.DESdecode(REQUESTER,key);
        INTERFACE_TIME = encryptUtil.DESdecode(INTERFACE_TIME,key);
        INTERFACE_RESULT = encryptUtil.DESdecode(INTERFACE_RESULT,key);
        INTERFACE_CONDITION = encryptUtil.DESdecode(INTERFACE_CONDITION,key);

        INTERFACE_NAME = encryptUtil.DESdecode(INTERFACE_NAME,key);
        ORGANIZATION_ID = encryptUtil.DESdecode(ORGANIZATION_ID,key);
        ERROR_CODE = encryptUtil.DESdecode(ERROR_CODE,key);
        INTERFACE_NUMBER = encryptUtil.DESdecode(INTERFACE_NUMBER,key);
        INTERFACE_TABLE = encryptUtil.DESdecode(INTERFACE_TABLE,key);
        INTERFACE_KEY = encryptUtil.DESdecode(INTERFACE_KEY,key);

        UserInfo userInfo = JSONObject.parseObject(USER_INFO,UserInfo.class);

        InterfaceLog interfaceLog = new InterfaceLog(
                Util.get32UUID(),
                PoliceConstant.REG_ID,
                TERMINAL_ID,
                Util.getNowDateTimeStr(INTERFACE_TIME),
                REQUESTER,
                INTERFACE_RESULT,
                INTERFACE_CONDITION);
        //设置可选条件
        interfaceLog.setUSER_ID(userInfo.getId())
                .setORGANIZATION(userInfo.getDepartmentname())
                .setUSER_NAME(userInfo.getLastname())
                .setINTERFACE_NAME(INTERFACE_NAME)
                .setORGANIZATION_ID(ORGANIZATION_ID)
                .setERROR_CODE(ERROR_CODE)
                .setINTERFACE_NUMBER(Util.getInt(INTERFACE_NUMBER))
                .setINTERFACE_TABLE(INTERFACE_TABLE)
                .setINTERFACE_KEY(INTERFACE_KEY);

        String status =  jedisUtil.setPreFix(PoliceConstant.INTERFACE_LOG_PREFIX,Util.get16UUID(),JSONObject.toJSONString(interfaceLog));
        return RestResponse.result(status);
    }
}
