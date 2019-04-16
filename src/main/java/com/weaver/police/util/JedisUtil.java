package com.weaver.police.util;

import com.weaver.police.excuter.SubscriberListener;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * @Author      :wyl
 * @Date        :2019/4/9  14:35
 * @Version 1.0 :
 * @Description :
 **/
@Component
public class JedisUtil {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private SubscriberListener subscribe;


    @Value("${spring.redis.channel}")
    private String configChannel;

    @Value("${spring.redis.expireTime}")
    private Integer expireTime;

    @Value("spring.redis.prefix")
    private String prefix;

    private static final Logger log = LoggerFactory.getLogger(JedisUtil.class);



    /**
     * 获取缓存
     * @param key 键
     * @return 值
     */
    public String get(String key){
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(jedis.exists(key)){
                value = jedis.get(key);
                value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
                log.debug("get {} = {}",key,value);
            }
        } catch (JedisException e) {
            log.warn("get {} ", key, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Object getObject(String key){
        Object value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(jedis.exists(key.getBytes())){
                value = toObject(jedis.get(key.getBytes()));
                log.debug("get {} = {}", key, value);
            }
        } catch (JedisException e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    public String set(String key, String value) {
        return set(key, value, expireTime);
    }

    /**
     * 设置自定义key前缀
     * @param prefix
     * @param key
     * @param value
     * @return
     */
    public String setPreFix(String prefix,String key,String value){
        return set(prefix+key,value);
    }

    /**
     * 设置带默认前缀的key
     * @param key
     * @param value
     * @return
     */
    public String setPreFix(String key,String value){
        return set(prefix+key,value);
    }

    /**
     * 设置带默认前缀的key,缓存时间
     * @param key
     * @param value
     * @param cacheSeconds
     * @return
     */
    public String setPreFix(String key,String value,int cacheSeconds){
        return set(prefix+key,value,cacheSeconds);
    }

    /**
     * 设置缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return result
     */
    public String set(String key, String value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.set(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            log.debug("set {} = {}", key, value);
        } catch (Exception e) {
            log.warn("set {} ", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }


    /**
     * @param keyPrefix
     * @return
     */
    public List<String> getStringListByKeyPrefix(String keyPrefix) {
        List<String> value = new ArrayList<>();
        Jedis jedis = null;
        try {
            jedis = getResource();
            Set<String> keys = getKeysByPrefix(keyPrefix);
            if (keys != null && keys.size() > 0) {
                for (String key : keys) {
                    String val = get(key);
                    value.add(val);

                }

            }
        } catch (Exception e) {
            log.warn("getObjectList {} ", keyPrefix, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 获取以指定开头前缀的所有Key
     * @param keyPrefix
     * @return
     */
    public Set<String> getKeysByPrefix(String keyPrefix) {
        Set<String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.keys(keyPrefix + "*");
            log.debug("getSet {} ", keyPrefix, value);
        } catch (Exception e) {
            log.warn("getSet {} ", keyPrefix, e);
        } finally {
            returnResource(jedis);
        }
        return value;

    }

    /**
     * 删除缓存
     *
     * @param key 键
     * @return
     */
    public long del(String key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                result = jedis.del(key);
                log.debug("del {}", key);
            } else {
                log.debug("del {} not exists", key);
            }
        } catch (Exception e) {
            log.warn("del {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }


    /**
     * 获取Map缓存
     *
     * @param key 键
     * @return 值
     */
    public Map<String, String> getMap(String key) {
        Map<String, String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.hgetAll(key);
                log.debug("getMap {} ", key, value);
            }
        } catch (Exception e) {
            log.warn("getMap {} ", key, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 推送消息
     * @param msg
     * @return
     */
    public Long publish(byte []msg) {
        Long value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.publish(configChannel.getBytes(), msg);
            log.debug("publish {} = {}",configChannel.getBytes(),msg);
        } catch (JedisException e) {
            e.printStackTrace();
            log.warn("publish {} ", e);
        }finally {
            returnResource(jedis);
        }
        return value;
    }

    public Long publish(byte []channel,byte []msg) {
        Long value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.publish(channel, msg);
            log.debug("publish {} = {}",channel,msg);
        } catch (JedisException e) {
            e.printStackTrace();
            log.warn("publish {} ", e);
        }finally {
            returnResource(jedis);
        }
        return value;
    }

    public Long publish(String msg) {
        Long value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.publish(configChannel, msg);
            log.debug("publish {} = {}",configChannel,msg);
        } catch (JedisException e) {
            e.printStackTrace();
            log.warn("publish {} ", e);
        }finally {
            returnResource(jedis);
        }
        return value;
    }

    public Long publish(String channel,String msg) {
        Long value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.publish(channel, msg);
            log.debug("publish {} = {}",channel,channel);
        } catch (JedisException e) {
            e.printStackTrace();
            log.warn("publish {} ", e);
        }finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 订阅消息
     */
    public void subscribe(){
        Jedis jedis = null;
        try {
            jedis = getResource();
            subscribe(subscribe, configChannel);
        } catch (JedisException e) {
            e.printStackTrace();
            log.warn("publish {} ", e);
        }finally {
            returnResource(jedis);
        }
    }

    public void subscribe(JedisPubSub subscribe){
        Jedis jedis = null;
        try {
            jedis = getResource();
            subscribe(subscribe, configChannel);
        } catch (JedisException e) {
            e.printStackTrace();
            log.warn("publish {} ", e);
        }finally {
            returnResource(jedis);
        }
    }

    public void subscribe(String ...channel){
        Jedis jedis = null;
        try {
            jedis = getResource();
            subscribe(subscribe, channel);
        } catch (JedisException e) {
            e.printStackTrace();
            log.warn("publish {} ", e);
        }finally {
            returnResource(jedis);
        }
    }

    public void subscribe(JedisPubSub subscribe,String ...channel){
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.subscribe(subscribe, channel);
        } catch (JedisException e) {
            e.printStackTrace();
            log.warn("publish {} ", e);
        }
    }


    /**
     * 获取资源
     * @return
     * @throws JedisException
     */
    public Jedis getResource() throws JedisException{
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

        } catch (JedisException e) {
            e.printStackTrace();
            throw e;
        }
        return jedis;
    }

    /**
     * 释放资源
     *
     * @param jedis
     */
    public void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


    /**
     * Object转换byte[]类型
     *
     * @param object
     * @return
     */
    public byte[] toBytes(Object object) {
        return Util_Serialization.serialize(object);
    }

    /**
     * byte[]型转换Object
     *
     * @param bytes
     * @return
     */
    public Object toObject(byte[] bytes) {
        return Util_Serialization.deserialize(bytes);
    }

}
