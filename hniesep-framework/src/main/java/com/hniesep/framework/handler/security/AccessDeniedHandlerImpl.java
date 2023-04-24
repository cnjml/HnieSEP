package com.hniesep.framework.handler.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.protocol.HttpResultEnum;
import com.hniesep.framework.util.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author HKRR
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    ObjectMapper objectMapper;
    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();
        ResponseResult<Object> result = new ResponseResult<>(HttpResultEnum.NO_PERMISSION);
        WebUtils.renderString(response,objectMapper.writeValueAsString(result));
    }
}
