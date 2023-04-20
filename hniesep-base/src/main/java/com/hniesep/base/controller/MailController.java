package com.hniesep.base.controller;

import com.hniesep.base.entity.bo.Mail;
import com.hniesep.base.entity.vo.ResponseVO;
import com.hniesep.base.protocol.StatusCode;
import com.hniesep.base.protocol.StatusMessage;
import com.hniesep.base.service.impl.ArticleServiceImpl;
import com.hniesep.base.util.MailUtil;
import com.hniesep.base.util.RegexUtil;
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
    public ResponseVO sendVerifyCode(@RequestBody Mail mail) {
        boolean emailValidFlag = RegexUtil.validEmail(mail.getEmail());
        Integer code = emailValidFlag ? StatusCode.EMAIL_LEGITIMATE : StatusCode.EMAIL_ILLEGAL;
        String msg = emailValidFlag ? StatusMessage.EMAIL_LEGITIMATE : StatusMessage.EMAIL_ILLEGAL;
        if (emailValidFlag) {
            boolean sendVerificationCodeFlag = mailUtil.sendVerificationCode(mail.getEmail());
            code = sendVerificationCodeFlag ? StatusCode.SEND_VERIFICATION_CODE_OK : StatusCode.SEND_VERIFICATION_CODE_ERR;
            msg = sendVerificationCodeFlag ? StatusMessage.SEND_VERIFICATION_CODE_OK : StatusMessage.SEND_VERIFICATION_CODE_ERR;
        }
        return new ResponseVO(code, msg);
    }
}