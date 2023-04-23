package com.hniesep.framework.service.impl;

import com.hniesep.framework.entity.Account;
import com.hniesep.framework.service.RegisterService;
import com.hniesep.framework.exception.ServiceException;
import com.hniesep.framework.mapper.AccountMapper;
import com.hniesep.framework.protocol.Autograph;
import com.hniesep.framework.util.RedisUtil;
import com.hniesep.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 吉铭炼
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    private AccountMapper accountMapper;
    private RedisUtil redisUtil;
    private StringUtil stringUtil;

    @Autowired
    public void setAccountUtil(StringUtil stringUtil) {
        this.stringUtil = stringUtil;
    }

    @Autowired
    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public int register(String email, String username, String password, Date regTime) {
        String md5Password = stringUtil.generateMd5String(password, Autograph.PASSWORD_SALT);
        Account account = new Account();
        account.setAccountEmail(email);
        account.setAccountUsername(username);
        account.setAccountPassword(md5Password);
        account.setAccountRegisterTime(regTime);
        return accountMapper.insert(account);
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String bcryptPassword = passwordEncoder.encode("123456");
        System.out.println(bcryptPassword);
    }

    @Override
    public void setRegisterVerificationCode(String toAddress, String verificationCode) {
        String autograph = Autograph.VERIFICATION_CODE_SIGNATURE;
        if (redisUtil.get(toAddress) != null) {
            throw new ServiceException("验证码还在有效期内");
        } else {
            redisUtil.set(toAddress, autograph + verificationCode);
        }
    }

    @Override
    public boolean checkRegisterVerificationCode(String email, String verificationCode) {
        String realVerificationCode = this.getRegisterVerificationCode(email);
        if (realVerificationCode == null) {
            return false;
        } else {
            return realVerificationCode.equals(Autograph.VERIFICATION_CODE_SIGNATURE + verificationCode);
        }
    }

    @Override
    public String getRegisterVerificationCode(String email) {
        return redisUtil.get(email);
    }
}