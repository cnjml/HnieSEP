package com.hniesep.base.util;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * @author 吉铭炼
 */
@Component
public class AccountUtil {
    public String generateMd5String(String rawString,String salt){
        rawString = rawString+salt;
        return DigestUtils.md5DigestAsHex((rawString+salt).getBytes());
    }
}