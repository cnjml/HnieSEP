package com.hniesep.framework.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @author 吉铭炼
 */
@Component
public class StringUtil {
    /**
     * 校验邮箱是否合法
     *
     * @param email 邮件地址
     * @return 是否合法
     */
    public static boolean isValidEmail(String email) {
        if ((email != null) && (!email.isEmpty())) {
            return Pattern.matches("^[A-Za-z0-9\\u4e00-\\u9fa5_-]+@[a-zA-Z0-9_-][-a-zA-Z0-9_-]{0,62}(?:\\.[a-zA-Z0-9_-][-a-zA-Z0-9_-]{0,62})+$", email);
        }
        return false;
    }

    /**
     * 校验用户名是否合法
     *
     * @param username 用户名
     * @return 是否合法
     */
    public static boolean isValidUsername(String username) {
        if ((username != null) && (!username.isEmpty())) {
            return Pattern.matches("^[a-zA-Z0-9_-]{4,20}$", username);
        }
        return false;
    }

    /**
     * 校验密码是否合法
     *
     * @param password 密码
     * @return 是否合法
     */
    public static boolean isValidPassword(String password) {
        if ((password != null) && (!password.isEmpty())) {
            return Pattern.matches("^(?=.*[0-9])(?=.*[a-zA-Z])[0-9A-Za-z~!@#$%^&*._?]{6,16}$", password);
        }
        return false;
    }

    /**
     * 生成bcrypt字符串
     * @param rawString 初始字符串
     * @return md5字符串
     */
    public static String generateBcrypt(String rawString){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawString);
    }

}
