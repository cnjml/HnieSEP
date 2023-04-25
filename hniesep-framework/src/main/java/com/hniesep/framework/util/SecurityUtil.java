package com.hniesep.framework.util;

import com.hniesep.framework.entity.vo.UserVO;
import com.hniesep.framework.protocol.FieldCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author 吉铭炼
 */
@Component
public class SecurityUtil {
    public static UserVO getUserVO(){
        return (UserVO) getAuthentication().getPrincipal();
    }
    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
    public static boolean isAdmin(){
        return getUserVO().getAccount().getAccountType().equals(FieldCode.ACCOUNT_TYPE_ADMIN);
    }
    public static Long getAccountId(){
        return getUserVO().getAccount().getAccountId();
    }
}
