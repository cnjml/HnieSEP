package com.hniesep.base.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import com.hniesep.base.account.mapper.UserMapper;
import com.hniesep.base.account.service.RegisterService;
import com.hniesep.base.util.RedisUtil;
import com.hniesep.base.util.VerificationUtil;

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
    public void register(String username, String password, Date regTime) {
        userMapper.insert(username, password,regTime);
    }
    @Override
    public void setVerificationCode(String toAddress) {
        String verificationCode = VerificationUtil.generateVerificationCode();
        redisUtil.setVerificationCode(toAddress,verificationCode);
    }

    @Override
    public boolean checkVerificationCode() {

        return false;
    }
}