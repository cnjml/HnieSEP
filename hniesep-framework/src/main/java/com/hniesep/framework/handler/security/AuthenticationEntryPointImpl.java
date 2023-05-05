package com.hniesep.framework.handler.security;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.protocol.HttpResultEnum;
import com.hniesep.framework.util.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
 * @author 吉铭炼
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();
        if(authException instanceof BadCredentialsException){
            ResponseResult<Object> result = new ResponseResult<>(HttpResultEnum.CREDENTIALS_EXPIRE);
            WebUtils.renderString(response,JSON.toJSONString(result));
        }
        else if(authException instanceof InsufficientAuthenticationException) {
            ResponseResult<Object> result = new ResponseResult<>(HttpResultEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
        else {
            ResponseResult<Object> result = new ResponseResult<>(HttpResultEnum.FAILED.getCode(),authException.getMessage());
            WebUtils.renderString(response,JSON.toJSONString(result));
        }
    }
}
