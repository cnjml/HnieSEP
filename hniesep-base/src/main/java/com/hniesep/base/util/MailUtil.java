package com.hniesep.base.util;

import com.hniesep.base.account.service.impl.RegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author 吉铭炼
 */
@Service
public class MailUtil {
    @Value("${spring.mail.username}")
    private String fromAddress;
    @Value("${spring.mail.nickname}")
    private String nickname;
    private JavaMailSender javaMailSender;
    private TemplateEngine templateEngine;
    private RegisterServiceImpl registerService;
    @Autowired
    public void setRegisterService(RegisterServiceImpl registerService){
        this.registerService = registerService;
    }
    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @Autowired
    public void setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    /**
     * 发送验证码到指定邮箱
     * @param toAddress 邮箱地址
     * @return 发送结果
     */
    public boolean sendVerificationCode(String toAddress) {
        //调用 VerificationCodeService 生产验证码
        String verificationCode = VerificationUtil.generateVerificationCode();
        //创建邮件正文
        Context context = new Context();
        context.setVariable("verifyCode", Arrays.asList(verificationCode.split("")));
        //将模块引擎内容解析成html字符串
        String emailContent = templateEngine.process("VerifyCode", context);
        MimeMessage message=javaMailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper= new MimeMessageHelper(message,true);
            helper.setFrom(nickname+'<'+fromAddress+'>');
            helper.setTo(toAddress);
            helper.setSubject("注册验证码");
            helper.setText(emailContent,true);
            javaMailSender.send(message);
            //redis设置验证码
            registerService.setRegisterVerificationCode(toAddress,verificationCode);
            return true;
        }catch (MessagingException ignored) {
            return false;
        }
    }
    /**
     * 校验邮箱是否合法
     * @param email 邮件地址
     * @return 是否合法
     */
    public boolean checkEmailLegality(String email) {
        if ((email != null) && (!email.isEmpty())) {
            return Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email);
        }
        return false;
    }
}