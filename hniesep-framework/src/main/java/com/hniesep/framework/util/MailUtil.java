package com.hniesep.framework.util;

import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.bo.MailBO;
import com.hniesep.framework.exception.SystemException;
import com.hniesep.framework.protocol.HttpResultEnum;
import com.hniesep.framework.service.impl.RegisterServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author 吉铭炼
 */
@Component
public class MailUtil {
    @Value("${spring.mail.username}")
    private String fromAddress;
    @Value("${spring.mail.nickname}")
    private String nickname;
    private JavaMailSender javaMailSender;
    private TemplateEngine templateEngine;
    private RegisterServiceImpl registerService;
    private RedisUtil redisUtil;
    @Autowired
    public void setRedisUtil(RedisUtil redisUtil){
        this.redisUtil = redisUtil;
    }
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
     * @param mailBO 邮箱业务对象
     * @return 发送结果
     */
    public ResponseResult<Object> sendVerificationCode(MailBO mailBO) {
        String toAddress = mailBO.getEmail();
        if(!StringUtil.isValidEmail(toAddress)){
            throw new SystemException(HttpResultEnum.ARGUMENTS_ERROR);
        }
        //调用 VerificationUtil 生成验证码
        String verificationCode = VerificationUtil.generateVerificationCode();
        //创建邮件正文
        Context context = new Context();
        context.setVariable("verifyCode", Arrays.asList(verificationCode.split("")));
        //将模块引擎内容解析成html字符串
        String emailContent = templateEngine.process("VerifyCode", context);
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(nickname + '<' + fromAddress + '>');
            helper.setTo(toAddress);
            helper.setSubject("注册验证码");
            helper.setText(emailContent,true);
            //存储验证码到redis
            registerService.setRegisterVerificationCode(toAddress, verificationCode);
            javaMailSender.send(message);
            return ResponseResult.success();
        }catch (MessagingException ignored) {
            return ResponseResult.fail();
        }
    }

}
