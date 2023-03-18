package com.hniesep.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hniesep.base.protocol.StatusMessage;
import com.hniesep.base.entity.Result;
import com.hniesep.base.protocol.StatusCode;
import com.hniesep.base.entity.Mail;
import com.hniesep.base.util.MailUtil;

/**
 * @author 吉铭炼
 */
@Controller
@RequestMapping("/mail")
public class MailController {
    private final MailUtil mailUtil;
    @Autowired
    private MailController (MailUtil mailUtil){
        this.mailUtil = mailUtil;
    }
    @PostMapping("/sendVerifyCode")
    @ResponseBody
    public Result sendVerifyCode(@RequestBody Mail mail){
        boolean emailValidFlag = mailUtil.checkEmailLegality(mail.getToAddress());
        Integer code = emailValidFlag ? StatusCode.EMAIL_LEGITIMATE:StatusCode.EMAIL_ILLEGAL;
        String msg = emailValidFlag ? StatusMessage.EMAIL_LEGITIMATE: StatusMessage.EMAIL_ILLEGAL;
        if(emailValidFlag){
            boolean sendVerificationCodeFlag = mailUtil.sendVerificationCode(mail.getToAddress());
            code = sendVerificationCodeFlag ? StatusCode.SEND_VERIFICATION_CODE_OK:StatusCode.SEND_VERIFICATION_CODE_ERR;
            msg = sendVerificationCodeFlag ? StatusMessage.SEND_VERIFICATION_CODE_OK: StatusMessage.SEND_VERIFICATION_CODE_ERR;
        }
        return new Result(code,msg);
    }
}