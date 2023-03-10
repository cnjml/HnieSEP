package com.hniesep.base.controller;

import com.hniesep.base.common.Msg;
import com.hniesep.base.common.Result;
import com.hniesep.base.common.StatusCode;
import com.hniesep.base.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 吉铭炼
 */
@Controller
@RequestMapping("/mail")
public class MailController {
    private final MailUtil mailUtil;
    @PostMapping("/sendVerifyCode")
    @ResponseBody
    public Result sendVerifyCode(@RequestBody String toAddress){
        boolean flag = mailUtil.sendVerificationCode(toAddress);
        Integer code = flag ? StatusCode.SENDVERIFYCODE_OK:StatusCode.SENDVERIFYCODE_ERR;
        String msg = flag ? Msg.SENDVERIFYCODE_OK:Msg.SENDVERIFYCODE_ERR;
        return new Result(code,msg);
    }
    @Autowired
    private MailController (MailUtil mailUtil){
        this.mailUtil = mailUtil;
    }
}