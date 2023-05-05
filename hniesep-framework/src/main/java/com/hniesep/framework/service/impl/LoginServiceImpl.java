package com.hniesep.framework.service.impl;

import com.hniesep.framework.entity.Account;
import com.hniesep.framework.entity.bo.UserBO;
import com.hniesep.framework.entity.vo.UserVO;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.mapper.AccountMapper;
import com.hniesep.framework.protocol.HttpResultEnum;
import com.hniesep.framework.service.LoginService;
import com.hniesep.framework.util.JwtUtil;
import com.hniesep.framework.util.RedisCache;
import com.hniesep.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author 吉铭炼
 */
@Service
public class LoginServiceImpl implements LoginService {
    private AuthenticationManager authenticationManager;
    private AccountMapper accountMapper;
    private RedisCache redisCache;
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }
    @Autowired
    public void setRedisCache(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    @Autowired
    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public boolean loginByEmail(String email, String password) {
        String md5Password = StringUtil.generateBcrypt(password);
        return accountMapper.loginByEmail(email, md5Password) != null;
    }

    @Override
    public ResponseResult<UserVO> login(UserBO userBO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userBO.getUsername(), userBO.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (!Objects.nonNull(authentication)) {
            throw new RuntimeException(HttpResultEnum.LOGIN_ERROR.getMsg());
        }
        Account account = (Account) authentication.getPrincipal();
        Long userId = account.getAccountId();
        redisCache.setCacheObject("loginUser:" + userId, account);
        UserVO userVO = new UserVO(account);
        String token = JwtUtil.createJwt(userId.toString(), TimeUnit.HOURS.toMillis(1));
        userVO.setToken(token);
        return ResponseResult.success(userVO);
    }
    @Override
    public ResponseResult<Object> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();
        Long userId = account.getAccountId();
        redisCache.deleteObject("loginUser:"+userId);
        return ResponseResult.success();
    }

}
