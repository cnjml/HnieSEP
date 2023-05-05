package com.hniesep.user.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hniesep.framework.entity.Account;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.vo.UserVO;
import com.hniesep.framework.protocol.HttpResultEnum;
import com.hniesep.framework.util.JwtUtil;
import com.hniesep.framework.util.RedisCache;
import com.hniesep.framework.util.WebUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * @author 吉铭炼
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private RedisCache redisCache;
    private ObjectMapper objectMapper;
    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }
    @Autowired
    public void setRedisCache(RedisCache redisCache){
        this.redisCache = redisCache;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        Claims claims = null;
        try {
            //没有token请求头，直接返回
            if(!StringUtils.hasText(token)){
                filterChain.doFilter(request,response);
                return;
            }
            //解析token
            claims = JwtUtil.parseJwt(token);
        }catch (Exception e){
            //解析token失败，因为token错误，返回需要登录
            String responseString = objectMapper.writeValueAsString(ResponseResult.fail(HttpResultEnum.NEED_LOGIN));
            WebUtils.renderString(response,responseString);
            return;
        }
        //获取用户id
        String userId = claims.getSubject();
        //根据userid从redis获取用户详细信息
        Account account = JSONObject.parseObject(redisCache.getCacheObject("loginUser:" +userId).toString(),Account.class);
        //数据是否为空
        if(Objects.isNull(account)){
            //退出登录或token到期，导致获取用户详细信息失败，返回授权过期
            String responseString = JSON.toJSONString(ResponseResult.fail(HttpResultEnum.CREDENTIALS_EXPIRE));
            WebUtils.renderString(response,responseString);
            return;
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(account,null,null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request,response);
    }
}
