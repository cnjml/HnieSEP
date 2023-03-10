package com.hniesep.base.controller;

import com.hniesep.base.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 吉铭炼
 */
@Controller
@RequestMapping("/mail")
public class MailController {
    private final MailUtil mailUtil;
    @PostMapping("/sendVerifyCode")
    public void sendVerifyCode(@RequestBody String toAddress){
        mailUtil.sendVerificationCode(toAddress);
    }
    @Autowired
    private MailController (MailUtil mailUtil){
        this.mailUtil = mailUtil;
    }
}