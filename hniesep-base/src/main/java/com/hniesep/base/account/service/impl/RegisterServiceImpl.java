package com.hniesep.base.account.service.impl;

import com.hniesep.base.util.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import com.hniesep.base.account.mapper.AccountMapper;
import com.hniesep.base.account.service.RegisterService;
import com.hniesep.base.util.RedisUtil;

/**
 * @author 吉铭炼
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    private AccountMapper accountMapper;
    private RedisUtil redisUtil;
    private AccountUtil accountUtil;
    @Autowired
    public void setAccountUtil(AccountUtil accountUtil){
        this.accountUtil = accountUtil;
    }
    @Autowired
    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }
    @Autowired
    public void setRedisUtil(RedisUtil redisUtil){
        this.redisUtil=redisUtil;
    }
    @Override
    public void register(String username, String password, String email, Date regTime) {
        String md5Password = accountUtil.generateMd5Password(password);
        accountMapper.insert(username, md5Password ,email ,regTime);
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