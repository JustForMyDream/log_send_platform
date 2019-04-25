package com.weaver.police.timer;

/*
 * @Author      :wyl
 * @Date        :2019/4/11  19:56
 * @Version 1.0 :
 * @Description :
 **/

import com.alibaba.fastjson.JSONObject;
import com.weaver.police.bean.InterfaceLog;
import com.weaver.police.bean.OperateLog;
import com.weaver.police.constant.PoliceConstant;
import com.weaver.police.service.SaveServcie;
import com.weaver.police.util.JedisUtil;
import com.weaver.police.util.TableUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

@Configuration
@EnableScheduling
@Component
public class TimerTask {

    @Autowired
    private SaveServcie saveServcie;

    @Autowired
    private JedisUtil jedisUtil;

    @Resource(name = "asyncServiceExecutor")
    private Executor executor;

    private static final Logger LOGGER = LoggerFactory.getLogger(TimerTask.class);


    /**
     * 创建表,每天0点执行
     */
    @Scheduled(cron = "0 0 0 * * ?")
//    @Scheduled(fixedRate = 60000*10)
    public void executeCreateTable(){

        List tableList = TableUtil.getTableList(PoliceConstant.OPERATE_LOG);

        LOGGER.info("<=============定时创建表开始============>");
        boolean flag1 = TableUtil.execute(PoliceConstant.OPERATE_LOG,tableList,PoliceConstant.OPERATE_LOG);
        boolean flag2 = TableUtil.execute(PoliceConstant.INTERFACE_LOG,tableList,PoliceConstant.INTERFACE_LOG);
        if(flag1){
            LOGGER.info("<=============定时创建表成功============>");
            LOGGER.info("创建表sql：\n"+TableUtil.createTableByDate(PoliceConstant.OPERATE_LOG,tableList));
            LOGGER.info("创建序列seq：\n"+TableUtil.createSeqByDate(PoliceConstant.OPERATE_LOG));
            LOGGER.info("创建触发器tigger：\n"+TableUtil.createTiggerByDate(PoliceConstant.OPERATE_LOG,PoliceConstant.OPERATE_LOG));
        }else {
            LOGGER.info("<=============定时创建表失败============>");
        }

        if(flag2){
            LOGGER.info("<=============定时创建表成功============>");
            LOGGER.info("创建表sql：\n"+TableUtil.createTableByDate(PoliceConstant.INTERFACE_LOG,tableList));
            LOGGER.info("创建序列seq：\n"+TableUtil.createSeqByDate(PoliceConstant.INTERFACE_LOG));
            LOGGER.info("创建触发器tigger：\n"+TableUtil.createTiggerByDate(PoliceConstant.INTERFACE_LOG,PoliceConstant.INTERFACE_LOG));
        }else {
            LOGGER.info("<=============定时创建表失败============>");
        }

    }



    /**
     * 存储数据
     */
    @Scheduled(fixedRate = 60000*1)
    public void executeSaveDate(){

        Set<String> operateLogKeys = jedisUtil.getKeysByPrefix(PoliceConstant.OPERATE_LOG_PREFIX);
        Set<String> interfaceLogKeys = jedisUtil.getKeysByPrefix(PoliceConstant.INTERFACE_LOG_PREFIX);

        System.currentTimeMillis();
        Long startTime =System.currentTimeMillis();
        LOGGER.info("<======接收redis的数据======>");
        int operater_num = 0;
        int interface_num = 0;

        List<OperateLog> operateLogList = new ArrayList<>();
        List<InterfaceLog> interfaceLogList = new ArrayList<>();

        for(String key : operateLogKeys){
            String value = jedisUtil.get(key);
            OperateLog operateLog = JSONObject.parseObject(value,OperateLog.class);
            operateLogList.add(operateLog);
            operater_num ++;
        }
        try {
            boolean flag =  saveServcie.doSaveOperateLogWithDay(operateLogList);
            if(flag){
                for (String key : operateLogKeys){
                    jedisUtil.del(key);
                }
                LOGGER.info("<======存入【"+interface_num+"】条【interface_log】数据======>");
            }else {
                LOGGER.info("插入数据失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//
//        for(String key : operateLogKeys){
//            String value = jedisUtil.get(key);
//            OperateLog operateLog = JSONObject.parseObject(value,OperateLog.class);
//            try {
//                boolean flag = saveServcie.doSaveOperateLog(operateLog);
//                if(flag){
//                    jedisUtil.del(key);
//                }else {
//                    LOGGER.info("插入key【"+key+"】失败");
//                }
//                //启用线程池存数据
//
////                executor.execute(new SaveDataTask(saveServcie,operateLog));
////                jedisUtil.del(key);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        LOGGER.info("<======存入【"+operater_num+"】条【operate_log】数据======>");

        for(String key : interfaceLogKeys){
            String value = jedisUtil.get(key);
            InterfaceLog interfaceLog = JSONObject.parseObject(value,InterfaceLog.class);
            interfaceLogList.add(interfaceLog);
            interface_num ++;
        }
        try {
            boolean flag = saveServcie.doSaveInterfaceLogWithDay(interfaceLogList);
            if(flag){
                for (String key : interfaceLogKeys){
                    jedisUtil.del(key);
                }
                LOGGER.info("<======存入【"+interface_num+"】条【interface_log】数据======>");
            }else {
                LOGGER.info("插入数据失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//
//        for (String key : interfaceLogKeys){
//            String value = jedisUtil.get(key);
//            InterfaceLog interfaceLog = JSONObject.parseObject(value,InterfaceLog.class);
//            try {
//                boolean flag = saveServcie.doSaveInterfaceLog(interfaceLog);
//                if(flag){
//
//                }else {
//                    LOGGER.info("插入key【"+key+"】失败");
//                }
//                executor.execute(new SaveDataTask(saveServcie,interfaceLog));
//                jedisUtil.del(key);
//                interface_num ++;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        Long end =System.currentTimeMillis();

        LOGGER.info("<======共计耗时【"+(end -startTime)/1000+"】秒======>");;
    }
}
