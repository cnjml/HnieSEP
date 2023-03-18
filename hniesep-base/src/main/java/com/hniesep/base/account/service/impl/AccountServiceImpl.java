package com.hniesep.base.account.service.impl;

import com.hniesep.base.account.mapper.AccountMapper;
import com.hniesep.base.account.service.AccountService;
import com.hniesep.base.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 吉铭炼
 */
@Service
public class AccountServiceImpl implements AccountService {
    private AccountMapper accountMapper;
    @Autowired
    public void setAccountMapper(AccountMapper accountMapper){
        this.accountMapper=accountMapper;
    }
    @Override
    public boolean checkExist(String username ,String email) {
        return this.selectByName(username) != null || this.selectByEmail(email) != null;
    }
    @Override
    public User selectByName(String username) {
        return accountMapper.selectByName(username);
    }
    @Override
    public User selectByEmail(String email) {
        return accountMapper.selectByEmail(email);
    }
    @Override
    public List<User> selectAll() {
        return accountMapper.selectAll();
    }
    @Override
    public void setVerificationImage() {

    }
}