package com.hniesep.framework.service.impl;

import com.hniesep.framework.entity.bo.UserBO;
import com.hniesep.framework.entity.vo.UserVO;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.mapper.AccountMapper;
import com.hniesep.framework.protocol.Signature;
import com.hniesep.framework.protocol.StatusMessage;
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

import static com.hniesep.framework.protocol.Signature.PASSWORD_SALT;

/**
 * @author 吉铭炼
 */
@Service
public class LoginServiceImpl implements LoginService {
    private AuthenticationManager authenticationManager;
    private AccountMapper accountMapper;
    private StringUtil stringUtil;
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
    public void setStringUtil(StringUtil stringUtil) {
        this.stringUtil = stringUtil;
    }

    @Autowired
    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public boolean loginByName(String username, String password) {
        String md5Password = stringUtil.generateMd5String(password, PASSWORD_SALT);
        return accountMapper.loginByName(username, md5Password) != null;
    }

    @Override
    public boolean loginByEmail(String email, String password) {
        String md5Password = stringUtil.generateMd5String(password, PASSWORD_SALT);
        return accountMapper.loginByEmail(email, md5Password) != null;
    }

    @Override
    public boolean login(String account, String password) {
        return this.loginByEmail(account, password) || this.loginByName(account, password);
    }

    @Override
    public ResponseResult<UserVO> authLogin(UserBO userBO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userBO.getUsername(), userBO.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (!Objects.nonNull(authentication)) {
            throw new RuntimeException(StatusMessage.LOGIN_ERR);
        }
        UserVO userVO = (UserVO) authentication.getPrincipal();
        Integer userId = userVO.getAccount().getAccountId();
        String token = JwtUtil.createJwt(Long.valueOf(userId).toString());
        redisCache.setCacheObject(Signature.LOGIN_SECRET + userId, userVO);
        userVO.setToken(token);
        return ResponseResult.success(userVO);
    }
    @Override
    public ResponseResult<Object> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserVO userVO = (UserVO) authentication.getPrincipal();
        Long userId = Long.valueOf(userVO.getAccount().getAccountId());
        redisCache.deleteObject(Signature.LOGIN_SECRET+userId);
        return ResponseResult.success();
    }
}