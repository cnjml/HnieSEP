package com.hniesep.framework.service.impl;

import com.hniesep.framework.entity.bo.UserBO;
import com.hniesep.framework.entity.vo.UserVO;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.mapper.AccountMapper;
import com.hniesep.framework.service.LoginService;
import com.hniesep.framework.util.JwtUtil;
import com.hniesep.framework.util.RedisCache;
import com.hniesep.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.hniesep.framework.protocol.Autograph.PASSWORD_SALT;

/**
 * @author 吉铭炼
 */
@Service
public class LoginServiceImpl implements LoginService {
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    private AccountMapper accountMapper;
    private StringUtil stringUtil;
    private RedisCache redisCache;

    @Autowired
    public void setAuthenticationManager(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Autowired
    public void setAccountUtil(StringUtil stringUtil) {
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
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userBO.getUsername(), userBO.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (!Objects.nonNull(authentication)) {
            throw new RuntimeException("用户名或密码错误");
        }
        UserVO userVO = (UserVO) authentication.getPrincipal();
        Integer userId = userVO.getAccount().getAccountId();
        String token = JwtUtil.createJwt(Long.valueOf(userId).toString());
        redisCache.setCacheObject("jwtLogin" + userId, userVO);
        userVO.setToken(token);
        return ResponseResult.success(userVO);
    }

}