package com.hniesep.user.controller;

import com.hniesep.framework.entity.bo.mailBO;
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
    public ResponseResult sendVerifyCode(@RequestBody mailBO mailBO) {
        boolean emailValidFlag = StringUtil.validEmail(mailBO.getEmail());
        Integer code = emailValidFlag ? StatusCode.EMAIL_LEGITIMATE : StatusCode.EMAIL_ILLEGAL;
        String msg = emailValidFlag ? StatusMessage.EMAIL_LEGITIMATE : StatusMessage.EMAIL_ILLEGAL;
        if (emailValidFlag) {
            boolean sendVerificationCodeFlag = mailUtil.sendVerificationCode(mailBO.getEmail());
            code = sendVerificationCodeFlag ? StatusCode.SEND_VERIFICATION_CODE_OK : StatusCode.SEND_VERIFICATION_CODE_ERR;
            msg = sendVerificationCodeFlag ? StatusMessage.SEND_VERIFICATION_CODE_OK : StatusMessage.SEND_VERIFICATION_CODE_ERR;
        }
        return new ResponseResult<>(code, msg);
    }
}