package com.hniesep.base.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author 吉铭炼
 */
@Service
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
       return redisTemplate.opsForValue().get(key);
    }
}