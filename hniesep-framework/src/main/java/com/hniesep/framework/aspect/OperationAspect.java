package com.hniesep.framework.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author HKRR
 */
@Component
@Aspect
public class OperationAspect {
    @Pointcut("@annotation(com.hniesep.framework.annotation.GlobalInterceptor)")
    private void requestInterceptor() {}

    @Around("requestInterceptor()")
    public Object interceptorDo(ProceedingJoinPoint point) {
        try {
            Object target = point.getTarget();
            Object[] arguments = point.getArgs();
            String methodName = point.getSignature().getName();
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
