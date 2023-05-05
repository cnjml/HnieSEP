package com.hniesep.framework.util;

import com.hniesep.framework.entity.Account;
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
    public static Account getAccount(){
        return (Account) getAuthentication().getPrincipal();
    }
    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
    public static boolean isAdmin(){
        return getAccount().getAccountType().equals(FieldCode.ACCOUNT_TYPE_ADMIN);
    }
    public static Long getAccountId(){
        return getAccount().getAccountId();
    }
}
