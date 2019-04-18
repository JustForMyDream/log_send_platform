package com.weaver.police.excuter;

import com.alibaba.fastjson.JSONObject;
import com.weaver.police.bean.InterfaceLog;
import com.weaver.police.bean.OperateLog;
import com.weaver.police.constant.PoliceConstant;
import com.weaver.police.service.SaveServcie;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPubSub;

/*
 * @Author      :wyl
 * @Date        :2019/4/9  14:54
 * @Version 1.0 :
 * @Description :
 **/

@Component
public class SubscriberListener extends JedisPubSub {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberListener.class);

    @Autowired
    private SaveServcie saveServcie;

    @Override
    public void onMessage(String channel, String message) {
        if(StringUtils.equals(channel,PoliceConstant.OPERATE_LOG_CHANNEL)){ //监听用户操作
            try {
                saveServcie.doSaveOperateLog(JSONObject.parseObject(message,OperateLog.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(StringUtils.equals(channel,PoliceConstant.OPERATE_LOG_CHANNEL)){ //监听接口服务
            try {
                saveServcie.doSaveInterfaceLog(JSONObject.parseObject(message,InterfaceLog.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            LOGGER.info("no channel 【"+channel+"】,please check your send channel");
        }
    }
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {    //订阅了频道会调用
        System.out.println(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",
                channel, subscribedChannels));
    }
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {   //取消订阅 会调用
        System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d",
                channel, subscribedChannels));

    }
}
