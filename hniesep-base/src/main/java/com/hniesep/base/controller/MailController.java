package com.hniesep.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hniesep.base.protocol.Msg;
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
    @PostMapping("/sendVerifyCode")
    @ResponseBody
    public Result sendVerifyCode(@RequestBody Mail mail){
        boolean flag = mailUtil.sendVerificationCode(mail.getToAddress());
        Integer code = flag ? StatusCode.SEND_VERIFICATION_CODE_OK:StatusCode.SEND_VERIFICATION_CODE_ERR;
        String msg = flag ? Msg.SEND_VERIFICATION_CODE_OK:Msg.SEND_VERIFICATION_CODE_ERR;
        return new Result(code,msg);
    }
    @Autowired
    private MailController (MailUtil mailUtil){
        this.mailUtil = mailUtil;
    }
}