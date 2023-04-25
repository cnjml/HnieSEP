package com.hniesep.framework.service.impl;

import com.hniesep.framework.entity.Account;
import com.hniesep.framework.protocol.HttpResultEnum;
import com.hniesep.framework.service.RegisterService;
import com.hniesep.framework.exception.SystemException;
import com.hniesep.framework.mapper.AccountMapper;
import com.hniesep.framework.protocol.Signature;
import com.hniesep.framework.util.RedisUtil;
import com.hniesep.framework.util.StringUtil;
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
        String md5Password = stringUtil.generateMd5String(password, Signature.PASSWORD_SALT);
        Account account = new Account();
        account.setAccountEmail(email);
        account.setAccountUsername(username);
        account.setAccountPassword(md5Password);
        account.setAccountRegisterTime(regTime);
        return accountMapper.insert(account);
    }

    @Override
    public void setRegisterVerificationCode(String toAddress, String verificationCode) {
        String autograph = Signature.VERIFICATION_CODE_SIGNATURE;
        if (redisUtil.get(toAddress) != null) {
            throw new SystemException(HttpResultEnum.VERIFICATION_CODE_EXIST);
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
            return realVerificationCode.equals(Signature.VERIFICATION_CODE_SIGNATURE + verificationCode);
        }
    }

    @Override
    public String getRegisterVerificationCode(String email) {
        return redisUtil.get(email);
    }
}