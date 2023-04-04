package com.hniesep.base.account.service.impl;

import com.hniesep.base.account.mapper.AccountMapper;
import com.hniesep.base.account.service.AccountService;
import com.hniesep.base.entity.User;

import com.hniesep.base.protocol.Autograph;
import com.hniesep.base.util.AccountUtil;
import com.hniesep.base.util.VerificationUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 吉铭炼
 */
@Service
public class AccountServiceImpl implements AccountService {
    private AccountMapper accountMapper;
    private AccountUtil accountUtil;
    @Autowired
    public void setAccountUtil(AccountUtil accountUtil){
        this.accountUtil = accountUtil;
    }
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
    public void setVerificationImage(String realCode, HttpServletResponse httpServletResponse) {
       VerificationUtil.generateVerificationImage(realCode,httpServletResponse);
    }
    @Override
    public boolean changePasswordByOldPassword(String account, String oldPassword,String newPassword){
        oldPassword = accountUtil.generateMd5String(oldPassword, Autograph.PASSWORD_SALT);
        newPassword = accountUtil.generateMd5String(newPassword, Autograph.PASSWORD_SALT);
        return accountMapper.changePasswordByOldPassword(account,oldPassword,newPassword);
    }
}