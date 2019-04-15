package com.weaver.police.excuter;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

/*
 * @Author      :wyl
 * @Date        :2019/4/10  17:53
 * @Version 1.0 :
 * @Description :
 **/
@Component
public class SubscriberExcuter implements InitializingBean {

    @Resource(name = "asyncServiceExecutor")
    private Executor executor;

    @Autowired
    private Subscriber subscriber;

    @Override
    public void afterPropertiesSet() throws Exception {
        executor.execute(subscriber);
    }
}
