package com.hniesep.framework.handler.exception;

import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 吉铭炼
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SystemException.class)
    public ResponseResult<Object> systemExceptionHandler(SystemException e){
        log.error("出现了异常!",e);
        return new ResponseResult<>(e.getCode(),e.getMessage());
    }
}
