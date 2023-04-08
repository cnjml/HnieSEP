package com.hniesep.base.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import com.hniesep.base.account.mapper.AccountMapper;
import com.hniesep.base.account.service.RegisterService;
import com.hniesep.base.util.RedisUtil;
import com.hniesep.base.protocol.Autograph;
import com.hniesep.base.util.AccountUtil;

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
    public boolean register(String email,String username, String password,  Date regTime) {
        String md5Password = accountUtil.generateMd5String(password,Autograph.PASSWORD_SALT);
        return accountMapper.insert(email,username ,md5Password,regTime);
    }
    @Override
    public void setRegisterVerificationCode(String toAddress,String verificationCode) {
        String autograph = Autograph.VERIFICATION_CODE_SIGNATURE;
        redisUtil.set(toAddress,autograph+verificationCode);
    }

    @Override
    public boolean checkRegisterVerificationCode(String email,String verificationCode) {
        String realVerificationCode = this.getRegisterVerificationCode(email);
        if(realVerificationCode==null){
            return false;
        }
        else {
            return realVerificationCode.equals(Autograph.VERIFICATION_CODE_SIGNATURE+verificationCode);
        }
    }

    @Override
    public String getRegisterVerificationCode(String email) {
        return redisUtil.get(email);
    }
}