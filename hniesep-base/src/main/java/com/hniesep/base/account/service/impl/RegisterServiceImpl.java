package com.hniesep.base.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import com.hniesep.base.account.mapper.UserMapper;
import com.hniesep.base.account.service.RegisterService;
import com.hniesep.base.util.RedisUtil;

/**
 * @author 吉铭炼
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    private UserMapper userMapper;
    private RedisUtil redisUtil;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Autowired
    public void setRedisUtil(RedisUtil redisUtil){
        this.redisUtil=redisUtil;
    }
    @Override
    public void register(String username, String password, String email, Date regTime) {
        userMapper.insert(username, password ,email ,regTime);
    }
    @Override
    public void setRegisterVerificationCode(String toAddress,String verificationCode) {
        redisUtil.set(toAddress,verificationCode);
    }

    @Override
    public boolean checkRegisterVerificationCode(String email,String verificationCode) {
        String realVerificationCode = this.getRegisterVerificationCode(email);
        if(realVerificationCode==null){
            return false;
        }
        else {
            return verificationCode.equals(realVerificationCode);
        }
    }

    @Override
    public String getRegisterVerificationCode(String email) {
        return redisUtil.get(email);
    }
}