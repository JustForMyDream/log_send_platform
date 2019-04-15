package com.weaver.police.excuter;

import com.weaver.police.constant.PoliceConstant;
import com.weaver.police.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/*
 * @Author      :wyl
 * @Date        :2019/4/9  14:55
 * @Version 1.0 :
 * @Description :
 **/

@Component
public class Subscriber implements Runnable{
    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public void run() {
        jedisUtil.subscribe(PoliceConstant.OPERATE_LOG_CHANNEL,PoliceConstant.INTERFACE_LOG_CHANNEL);
    }

}
