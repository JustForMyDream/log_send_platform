package com.weaver.police.excuter;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

@Configuration
@EnableScheduling
@Component
public class RedisTask {

    @Autowired
    private SaveServcie saveServcie;

    @Autowired
    private JedisUtil jedisUtil;

    @Resource(name = "asyncServiceExecutor")
    private Executor executor;

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTask.class);


    /**
     * 一分钟执行一次
     */
    @Scheduled(fixedRate = 60000*1)
    public void excute(){

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
            saveServcie.doSaveOperateLog(operateLogList);
            for (String key : operateLogKeys){
                jedisUtil.del(key);
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
            saveServcie.doSaveInterfaceLog(interfaceLogList);
            for (String key : operateLogKeys){
                jedisUtil.del(key);
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
        LOGGER.info("<======存入【"+interface_num+"】条【interface_log】数据======>");
        Long end =System.currentTimeMillis();

        LOGGER.info("<======共计耗时【"+(end -startTime)+"】======>");;
    }
}
