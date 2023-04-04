package com.hniesep.base.util;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author 吉铭炼
 */
@Service
public class AccountUtil {
    public String generateMd5Password(String rawPassword,String salt){
        rawPassword = rawPassword+salt;
        return DigestUtils.md5DigestAsHex((rawPassword+salt).getBytes());
    }
}