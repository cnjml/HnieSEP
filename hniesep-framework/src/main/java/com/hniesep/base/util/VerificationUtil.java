package com.hniesep.base.util;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * @author 吉铭炼
 */
@Component
public class VerificationUtil {
    /**
     * generateVerificationCodeLength 生成的验证码的位数
     */
    private static final int DEFAULT_VERIFICATION_CODE_LENGTH =6;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String[] META_CODE ={"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static final Integer WIDTH = 100;
    /**
     * 干扰线数量
     */
    private static final Integer HEIGHT = 40;
    /**
     * 验证码长度
     */
    private static final Integer CODE_LENGTH = 6;
    /**
     * 干扰线数量
     */
    private static final Integer LINE_COUNT = 5;
    /**
     * 字体大小
     */
    private static final Integer FONT_SIZE = 20;
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
     */
    public static void generateVerificationImage(String realCode, HttpServletResponse httpServletResponse) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // 设置背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        // 绘制干扰线
        for (int i = 0; i < LINE_COUNT; i++) {
            int x1 = RANDOM.nextInt(WIDTH);
            int y1 = RANDOM.nextInt(HEIGHT);
            int x2 = RANDOM.nextInt(WIDTH);
            int y2 = RANDOM.nextInt(HEIGHT);
            g.setColor(new Color(RANDOM.nextInt(256), RANDOM.nextInt(256), RANDOM.nextInt(256)));
            g.drawLine(x1, y1, x2, y2);
        }
        // 绘制验证码
        for (int i = 0; i < realCode.length(); i++) {
            char c = realCode.charAt(i);
            g.setColor(new Color(RANDOM.nextInt(256), RANDOM.nextInt(256), RANDOM.nextInt(256)));
            g.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
            g.drawString(String.valueOf(c), i * (WIDTH / CODE_LENGTH) + 10, HEIGHT / 2 + FONT_SIZE / 2);
        }
        g.dispose();
        try {
            ImageIO.write(image,"JPG",httpServletResponse.getOutputStream());
        }catch (IOException e){
            System.out.println("创建验证码失败");
        }
    }
}