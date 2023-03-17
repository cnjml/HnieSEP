package com.hniesep.base.account.service.impl;

import com.hniesep.base.util.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.hniesep.base.account.service.LoginService;
import com.hniesep.base.account.mapper.AccountMapper;
import com.hniesep.base.entity.User;

/**
 * @author 吉铭炼
 */
@Service
public class LoginServiceImpl implements LoginService {
    AccountMapper accountMapper;
    AccountUtil accountUtil;
    @Autowired
    public void setAccountUtil(AccountUtil accountUtil){
        this.accountUtil = accountUtil;
    }
    @Autowired
    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }
    @Override
    public boolean login(String username, String password) {
        String md5Password = accountUtil.generateMd5Password(password);
        return accountMapper.select(username, md5Password) != null;
    }
    @Override
    public List<User> selectAll() {
        return accountMapper.selectAll();
    }
}