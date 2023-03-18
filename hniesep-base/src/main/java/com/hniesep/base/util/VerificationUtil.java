package com.hniesep.base.util;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
/**
 * @author 吉铭炼
 */
@Service
public class VerificationUtil {
    /**
     * generateVerificationCodeLength 生成的验证码的位数
     */
    private static final int DEFAULT_VERIFICATION_CODE_LENGTH =6;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String[] META_CODE ={"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    /**
     * 生成验证码
     * @return 字符验证码
     */
    public static String generateVerificationCode() {
        StringBuilder verificationCode = new StringBuilder();
        while (verificationCode.length()<DEFAULT_VERIFICATION_CODE_LENGTH){
            int i = RANDOM.nextInt(META_CODE.length-1);
            verificationCode.append(META_CODE[i]);
        }
        return verificationCode.toString();
    }
    /**
     * 生成图片验证码
     * @return 图片验证码
     */
    public static String generateVerificationImage(){
        return "";
    }
}