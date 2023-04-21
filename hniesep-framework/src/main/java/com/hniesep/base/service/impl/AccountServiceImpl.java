package com.hniesep.base.service.impl;

import com.hniesep.base.mapper.AccountMapper;
import com.hniesep.base.service.AccountService;
import com.hniesep.base.entity.bo.User;
import com.hniesep.base.protocol.Autograph;
import com.hniesep.base.util.StringUtil;
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
    private StringUtil stringUtil;
    @Autowired
    public void setAccountUtil(StringUtil stringUtil){
        this.stringUtil = stringUtil;
    }
    @Autowired
    public void setAccountMapper(AccountMapper accountMapper){
        this.accountMapper=accountMapper;
    }
    @Override
    public List<User> selectAll() {
        return accountMapper.selectAll();
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
    public void setVerificationImage(String realCode, HttpServletResponse httpServletResponse) {
       VerificationUtil.generateVerificationImage(realCode,httpServletResponse);
    }
    @Override
    public boolean changePasswordByEmailAndOldPassword(String account, String oldPassword,String newPassword){
        oldPassword = stringUtil.generateMd5String(oldPassword, Autograph.PASSWORD_SALT);
        newPassword = stringUtil.generateMd5String(newPassword, Autograph.PASSWORD_SALT);
        return accountMapper.changePasswordByOldPassword(account,oldPassword,newPassword);
    }
    @Override
    public boolean existUsername(String username) {
        return this.selectByName(username)!=null;
    }
    @Override
    public boolean existEmail(String email) {
        return this.selectByEmail(email)!=null;
    }
    @Override
    public boolean exist(String username ,String email) {
        return existUsername(username) || existEmail(email);
    }
}