package com.hniesep.framework.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hniesep.framework.annotation.SystemLog;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author 吉铭炼
 */
@Component
@Aspect
@Slf4j
public class AspectLog {
    private ObjectMapper objectMapper;
    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @Pointcut("@annotation(com.hniesep.framework.annotation.SystemLog)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object printLog(ProceedingJoinPoint pjp) throws Throwable {
        Object object = null;
        try {
            handleBefore(pjp);
            object = pjp.proceed();
            handleAfter(object);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 结束后换行
            log.info("==========End==========" + System.lineSeparator());
        }
        return object;
    }


    public void handleBefore(ProceedingJoinPoint pjp) throws JsonProcessingException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        //获取被增强的方法上的注解对象
        SystemLog systemLog = getSystemLog(pjp);
        log.info("=========Start=========");
        // 打印请求 URL
        log.info("URL            : {}", request.getRequestURL());
        // 打印描述信息
        log.info("BusinessName   : {}", systemLog.businessName());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", objectMapper.writeValueAsString(pjp.getArgs()));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        return methodSignature.getMethod().getAnnotation(SystemLog.class);
    }

    public void handleAfter(Object object) throws JsonProcessingException {
        // 打印出参
        log.info("Response       : {}", objectMapper.writeValueAsString(object));
    }
}
