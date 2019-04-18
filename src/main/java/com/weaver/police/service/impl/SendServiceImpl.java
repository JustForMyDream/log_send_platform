package com.weaver.police.service.impl;

import com.weaver.police.constant.PoliceConstant;
import com.weaver.police.service.SendService;
import com.weaver.police.util.JedisUtil;
import com.weaver.police.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*
 * @Author      :wyl
 * @Date        :2019/4/17  9:37
 * @Version 1.0 :
 * @Description :
 **/
@Service
public class SendServiceImpl implements SendService {

    @Autowired
    private JedisUtil jedisUtil;


    @Override
    public String setMessage(String prefix,String message) {
        return jedisUtil.setPreFix(prefix,Util.get16UUID(),message);
    }

    @Override
    public Long sendMessage(String channelName, String message) {
        return jedisUtil.publish(channelName,message);
    }
}
