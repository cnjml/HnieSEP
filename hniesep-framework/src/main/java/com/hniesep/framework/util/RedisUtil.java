package com.hniesep.framework.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author 吉铭炼
 */
@Component
public class RedisUtil {
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate){
        this.redisTemplate=redisTemplate;
    }
    public void set(String key,String value){
        redisTemplate.opsForValue().set(key,value,120,TimeUnit.SECONDS);
    }
    public String get(String key){
        System.out.println(redisTemplate.opsForValue().get(key));
       return redisTemplate.opsForValue().get(key);
    }
}