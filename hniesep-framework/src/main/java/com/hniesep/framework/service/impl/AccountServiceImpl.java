package com.hniesep.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hniesep.framework.entity.Account;
import com.hniesep.framework.entity.bo.UserBO;
import com.hniesep.framework.mapper.AccountMapper;
import com.hniesep.framework.service.AccountService;
import com.hniesep.framework.protocol.Signature;
import com.hniesep.framework.util.StringUtil;
import com.hniesep.framework.util.VerificationUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 吉铭炼
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
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
    public List<UserBO> selectAll() {
        return accountMapper.selectAll();
    }
    @Override
    public UserBO selectByName(String username) {
        return accountMapper.selectByName(username);
    }
    @Override
    public UserBO selectByEmail(String email) {
        return accountMapper.selectByEmail(email);
    }
    @Override
    public void setVerificationImage(String realCode, HttpServletResponse httpServletResponse) {
       VerificationUtil.generateVerificationImage(realCode,httpServletResponse);
    }
    @Override
    public boolean changePasswordByEmailAndOldPassword(String account, String oldPassword,String newPassword){
        oldPassword = stringUtil.generateMd5String(oldPassword, Signature.PASSWORD_SALT);
        newPassword = stringUtil.generateMd5String(newPassword, Signature.PASSWORD_SALT);
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