package com.hniesep.base.service.impl;

import com.hniesep.base.entity.Account;
import com.hniesep.base.service.RegisterService;
import com.hniesep.base.exception.ServiceException;
import com.hniesep.base.mapper.AccountMapper;
import com.hniesep.base.protocol.Autograph;
import com.hniesep.base.util.RedisUtil;
import com.hniesep.base.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
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