package com.hniesep.base.util;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;

/**
 * @author 吉铭炼
 */
@Service
public class VerifyUtil {
    /**
     * generateVerificationCodeLength 生成的验证码的位数
     *
     */
    private final int generateVerificationCodeLength=6;
    private final SecureRandom random = new SecureRandom();
    private final String[] metaCode={"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    public String generateVerificationCode() {
        StringBuilder verificationCode = new StringBuilder();
        while (verificationCode.length()<generateVerificationCodeLength){
            int i = random.nextInt(metaCode.length);
            verificationCode.append(metaCode[i]);
        }
        return verificationCode.toString();
    }
}