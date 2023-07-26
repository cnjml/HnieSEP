package com.hniesep.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hniesep.framework.entity.Account;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.bo.UserBO;
import com.hniesep.framework.entity.vo.UserInfoVO;
import com.hniesep.framework.exception.SystemException;
import com.hniesep.framework.mapper.AccountMapper;
import com.hniesep.framework.protocol.HttpResultEnum;
import com.hniesep.framework.service.AccountService;
import com.hniesep.framework.util.BeanUtil;
import com.hniesep.framework.util.SecurityUtil;
import com.hniesep.framework.util.StringUtil;
import com.hniesep.framework.util.VerificationUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 吉铭炼
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    private AccountMapper accountMapper;
    @Autowired
    public void setAccountMapper(AccountMapper accountMapper){
        this.accountMapper=accountMapper;
    }
    @Override
    public ResponseResult<List<Account>> selectAll() {
        return ResponseResult.success(list());
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
    public ResponseResult<Object> changePasswordByEmailAndOldPassword(UserBO userBO){
        String email = userBO.getEmail();
        String oldPassword = userBO.getOldPassword();
        String newPassword = userBO.getNewPassword();
        if(!StringUtil.isValidPassword(newPassword)){
            throw new SystemException(HttpResultEnum.ARGUMENTS_ERROR);
        }
        LambdaQueryWrapper<Account> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Account::getAccountEmail,email);
        Account account = accountMapper.selectOne(lambdaQueryWrapper);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //密码正确
        if(passwordEncoder.matches(oldPassword,account.getAccountPassword())){
            //设置新密码
            account.setAccountPassword(StringUtil.generateBcrypt(newPassword));
            accountMapper.updateById(account);
            return ResponseResult.success();
        }
        return ResponseResult.fail(HttpResultEnum.LOGIN_ERROR);
    }
    @Override
    public ResponseResult<Object> existUsername(String username) {
        if(selectByName(username)==null){
            return ResponseResult.success();
        }
        else {
            return ResponseResult.fail(HttpResultEnum.USERNAME_EXIST);
        }
    }
    @Override
    public ResponseResult<Object> existEmail(String email) {
        if(selectByEmail(email)==null){
            return ResponseResult.success();
        }
        else {
            return ResponseResult.fail(HttpResultEnum.EMAIL_EXIST);
        }
    }

    @Override
    public ResponseResult<UserInfoVO> getUserInfo(Long accountId) {
        UserInfoVO userInfoVO = BeanUtil.copyBean(accountMapper.selectById(accountId),UserInfoVO.class);
        return ResponseResult.success(userInfoVO);
    }

    @Override
    public ResponseResult<Object> updateUserInfo(UserBO userBO) {
        Account account = accountMapper.selectById(SecurityUtil.getAccountId());
        account.setAccountNickname(userBO.getNickname());
        accountMapper.updateById(account);
        return ResponseResult.success();
    }
}
