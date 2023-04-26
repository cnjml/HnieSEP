package com.hniesep.user.controller;

import com.hniesep.framework.entity.bo.MailBO;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.protocol.StatusCode;
import com.hniesep.framework.protocol.StatusMessage;
import com.hniesep.framework.util.MailUtil;
import com.hniesep.framework.util.StringUtil;
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
    private MailUtil mailUtil;

    @Autowired
    private void setMailUtil(MailUtil mailUtil) {
        this.mailUtil = mailUtil;
    }
    @PostMapping("/sendVerifyCode")
    @ResponseBody
    public ResponseResult<Object> sendVerifyCode(@RequestBody MailBO mailBO) {
        return mailUtil.sendVerificationCode(mailBO);
    }
}