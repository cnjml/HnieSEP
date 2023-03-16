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
    public void setVerificationCode(String regUsername,String verifyCode){
        redisTemplate.opsForValue().set(regUsername,verifyCode,120,TimeUnit.SECONDS);
    }

}