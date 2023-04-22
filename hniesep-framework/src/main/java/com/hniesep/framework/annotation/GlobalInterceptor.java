package com.hniesep.framework.annotation;

import java.lang.annotation.*;

/**
 * @author HKRR
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GlobalInterceptor {
    /**
     * 是否需要登录
     */
    boolean requireLogin() default false;

    /**
     * 参数校验
     */
    boolean validParas() default false;
}
