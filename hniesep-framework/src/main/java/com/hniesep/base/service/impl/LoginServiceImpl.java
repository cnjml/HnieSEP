package com.hniesep.base.service.impl;

import com.hniesep.base.mapper.AccountMapper;
import com.hniesep.base.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hniesep.base.service.LoginService;
import static com.hniesep.base.protocol.Autograph.PASSWORD_SALT;

/**
 * @author 吉铭炼
 */
@Service
public class LoginServiceImpl implements LoginService {
    AccountMapper accountMapper;
    StringUtil stringUtil;
    @Autowired
    public void setAccountUtil(StringUtil stringUtil){
        this.stringUtil = stringUtil;
    }
    @Autowired
    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }
    @Override
    public boolean loginByName(String username, String password) {
        String md5Password = stringUtil.generateMd5String(password,PASSWORD_SALT);
        return accountMapper.loginByName(username, md5Password) != null;
    }
    @Override
    public boolean loginByEmail(String email, String password) {
        String md5Password = stringUtil.generateMd5String(password,PASSWORD_SALT);
        return accountMapper.loginByEmail(email, md5Password) != null;
    }
    @Override
    public boolean login(String account, String password) {
        return this.loginByEmail(account,password) || this.loginByName(account,password);
    }
}