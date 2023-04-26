package com.hniesep.framework.service.impl;

import com.hniesep.framework.entity.Account;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.bo.UserBO;
import com.hniesep.framework.protocol.HttpResultEnum;
import com.hniesep.framework.service.RegisterService;
import com.hniesep.framework.exception.SystemException;
import com.hniesep.framework.mapper.AccountMapper;
import com.hniesep.framework.protocol.Signature;
import com.hniesep.framework.util.RedisUtil;
import com.hniesep.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

import static com.hniesep.framework.protocol.Signature.VERIFICATION_CODE_SIGNATURE;


/**
 * @author 吉铭炼
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    private AccountServiceImpl accountService;
    private AccountMapper accountMapper;
    private RedisUtil redisUtil;
    @Autowired
    public void setAccountService(AccountServiceImpl accountService){
        this.accountService = accountService;
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
    public ResponseResult<Object> register(UserBO userBO) {
        String email = userBO.getEmail();
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String verificationCode = userBO.getVerificationCode();
        //判断字段是否未空
        if(Objects.isNull(email)||Objects.isNull(password)||Objects.isNull(username)||Objects.isNull(verificationCode)){
            throw new SystemException(HttpResultEnum.ARGUMENTS_ERROR);
        }
        //判断字段是否合法
        if (!StringUtil.isValidPassword(password)||!StringUtil.isValidUsername(username)|| !StringUtil.isValidEmail(email)) {
            throw new SystemException(HttpResultEnum.ARGUMENTS_ERROR);
        }
        //判断用户名是否存在
        if(accountService.existUsername(username)){
            throw new SystemException(HttpResultEnum.USERNAME_EXIT);
        }
        //判断邮箱是否存在
        if(accountService.existEmail(email)){
            throw new SystemException(HttpResultEnum.EMAIL_EXIST);
        }
        //校验验证码，成功则直接注册
        if(this.checkRegisterVerificationCode(email,verificationCode)){
            Account account = new Account();
            account.setAccountEmail(email);
            account.setAccountUsername(username);
            String bcryptPassword = StringUtil.generateBcrypt(password);
            account.setAccountPassword(bcryptPassword);
            accountMapper.insert(account);
            return ResponseResult.success();
        }
        return ResponseResult.fail(HttpResultEnum.VERIFICATION_CODE_ERROR);
    }
    @Override
    public void setRegisterVerificationCode(String toAddress, String verificationCode) {
        if (redisUtil.get(VERIFICATION_CODE_SIGNATURE + toAddress) != null) {
            throw new SystemException(HttpResultEnum.VERIFICATION_CODE_EXIST);
        } else {
            redisUtil.set(VERIFICATION_CODE_SIGNATURE + toAddress, VERIFICATION_CODE_SIGNATURE + verificationCode);
        }
    }
    @Override
    public boolean checkRegisterVerificationCode(String email, String verificationCode) {
        String realVerificationCode = redisUtil.get(VERIFICATION_CODE_SIGNATURE + email);
        if (Objects.isNull(email) || !StringUtils.hasText(email)) {
            return false;
        } else {
            return realVerificationCode.equals(VERIFICATION_CODE_SIGNATURE + verificationCode);
        }
    }

}
