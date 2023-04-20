package com.hniesep.base.controller;

import com.hniesep.base.service.impl.AccountServiceImpl;
import com.hniesep.base.service.impl.LoginServiceImpl;
import com.hniesep.base.service.impl.RegisterServiceImpl;
import com.hniesep.base.entity.bo.User;
import com.hniesep.base.entity.vo.ResponseVO;
import com.hniesep.base.exception.ServiceException;
import com.hniesep.base.protocol.Autograph;
import com.hniesep.base.protocol.StatusCode;
import com.hniesep.base.protocol.StatusMessage;
import com.hniesep.base.util.DateTimeUtil;
import com.hniesep.base.util.RegexUtil;
import com.hniesep.base.util.VerificationUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 吉铭炼
 * user控制器
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    private LoginServiceImpl loginService;
    private RegisterServiceImpl registerService;
    private AccountServiceImpl accountService;
    @Autowired
    public void setLoginService(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }
    @Autowired
    public void setRegisterService(RegisterServiceImpl registerService) {
        this.registerService = registerService;
    }
    @Autowired
    public void setAccountService(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    /**
     * json注册
     *
     * @param user 请求体：一个json对象
     * @return 返回类
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseVO login(@RequestBody User user) {
        boolean flag = loginService.login(user.getEmail(), user.getPassword()) || loginService.login(user.getUsername(), user.getPassword());
        Integer code = flag ? StatusCode.LOGIN_OK : StatusCode.LOGIN_ERR;
        String msg = flag ? StatusMessage.LOGIN_OK : StatusMessage.LOGIN_ERR;
        return new ResponseVO(code, msg);
    }

    /**
     * 原始表单登录
     *
     * @param username 原始表单的username
     * @param password 原始表单的password
     * @return 返回类
     */
    @PostMapping("/originLogin")
    @ResponseBody
    @Deprecated
    public ResponseVO originLogin(@Param("username") String username, @Param("email") String email, @Param("password") String password) {
        boolean flag = loginService.login(username, password) || loginService.login(email, password);
        Integer code = flag ? StatusCode.LOGIN_OK : StatusCode.LOGIN_ERR;
        String msg = flag ? StatusMessage.LOGIN_OK : StatusMessage.LOGIN_ERR;
        return new ResponseVO(code, msg);
    }

    /**
     * json注册
     *
     * @param user 请求体：一个json对象
     * @return 返回类
     */
    @PostMapping("/isExist")
    @ResponseBody
    public ResponseVO isExist(@RequestBody User user) {
        boolean existFlag = accountService.exist(user.getUsername(), user.getEmail());
        Integer code = existFlag ? StatusCode.EXIST_TRUE : StatusCode.EXIST_FALSE;
        String msg = existFlag ? StatusMessage.EXIST_TRUE : StatusMessage.EXIST_FALSE;
        return new ResponseVO(code, msg);
    }

    /**
     * 检测用户名是否注册
     *
     * @param user 待检测用户对象
     * @return 用户名是否注册
     */
    @PostMapping("/existUsername")
    @ResponseBody
    public ResponseVO existUsername(@RequestBody User user) {
        boolean existFlag = accountService.existUsername(user.getUsername());
        Integer code = existFlag ? StatusCode.EXIST_USERNAME_ONLY_TRUE : StatusCode.EXIST_USERNAME_ONLY_FALSE;
        String msg = existFlag ? StatusMessage.EXIST_USERNAME_TRUE : StatusMessage.EXIST_USERNAME_FALSE;
        return new ResponseVO(code, msg);
    }

    /**
     * 检测邮箱是否注册
     *
     * @param user 待检测用户对象
     * @return 邮箱是否注册
     */
    @PostMapping("/existEmail")
    @ResponseBody
    public ResponseVO existEmail(@RequestBody User user) {
        boolean existFlag = accountService.existEmail(user.getEmail());
        Integer code = existFlag ? StatusCode.EXIST_EMAIL_ONLY_TRUE : StatusCode.EXIST_EMAIL_ONLY_FALSE;
        String msg = existFlag ? StatusMessage.EXIST_EMAIL_TRUE : StatusMessage.EXIST_EMAIL_FALSE;
        return new ResponseVO(code, msg);
    }

    /**
     * json注册
     *
     * @param user 请求体：一个json对象
     * @return 返回类
     */
    @PostMapping("/register")
    @ResponseBody
    public ResponseVO register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        if (!RegexUtil.validPassword(password)) {
            throw new ServiceException("密码格式错误");
        }
        if (!RegexUtil.validUsername(username)) {
            throw new ServiceException("用户名格式错误");
        }
        if (!RegexUtil.validEmail(email)) {
            throw new ServiceException("邮箱格式错误");
        }
        //判断验证码是否为空
        boolean emptyVerificationCodeFlag = user.getVerificationCode() == null || "".equals(user.getVerificationCode());
        Integer code = emptyVerificationCodeFlag ? StatusCode.VERIFICATION_CODE_EMPTY : StatusCode.VERIFICATION_CODE_EXIST;
        String msg = emptyVerificationCodeFlag ? StatusMessage.VERIFICATION_CODE_EMPTY : StatusMessage.VERIFICATION_CODE_EXIST;
        if (emptyVerificationCodeFlag) {
            return new ResponseVO(code, msg);
        }
        //判断用户名和邮箱是否存在
        boolean existFlag = accountService.exist(username, email);
        code = existFlag ? StatusCode.EXIST_TRUE : StatusCode.EXIST_FALSE;
        msg = existFlag ? StatusMessage.EXIST_TRUE: StatusMessage.EXIST_FALSE;

        //用户名和邮箱不存在则进行下一步注册
        if(!existFlag) {
            //校验验证码
            boolean checkVerificationCodeFlag = registerService.checkRegisterVerificationCode(email,user.getVerificationCode());
            code = checkVerificationCodeFlag ? StatusCode.CHECK_VERIFICATION_CODE_OK : StatusCode.CHECK_VERIFICATION_CODE_ERR;
            msg = checkVerificationCodeFlag ? StatusMessage.CHECK_VERIFICATION_CODE_OK : StatusMessage.CHECK_VERIFICATION_CODE_ERR;
            //校验成功则直接注册
            if(checkVerificationCodeFlag){
                boolean registerFlag = registerService.register(email,username,password,DateTimeUtil.getDateTime())>=1;
                code = registerFlag ? StatusCode.REGISTER_OK:StatusCode.REGISTER_ERR;
                msg = registerFlag ? StatusMessage.REGISTER_OK:StatusMessage.REGISTER_ERR;
            }
        }
        return new ResponseVO(code,msg);
    }
    /**
     * 获取所有用户，json格式
     * @return json对象
     */
    @GetMapping("/selectAll")
    @ResponseBody
    public String selectAll(){
        return accountService.selectAll().toString();
    }
    /**
     * 获取图片验证码
     */
    @GetMapping("/getVerificationImage")
    @ResponseBody
    public void verificationImage(HttpServletResponse httpServletResponse){
        String rightCode = VerificationUtil.generateVerificationCode();
        httpServletResponse.setHeader(Autograph.VERIFICATION_IMAGE_SIGNATURE + "verificationImageCode", rightCode);
        httpServletResponse.setContentType("image/jpeg");
        httpServletResponse.setHeader("Cache-Control","no-cache");
        accountService.setVerificationImage(rightCode,httpServletResponse);
    }
    /**
     * 校验图片验证码
     */
    @PostMapping("/checkVerificationImage")
    @ResponseBody
    public boolean checkVerificationImage(@Param("code") String code, HttpSession httpSession) {
        System.out.println(code);
        String realCode = (String) httpSession.getAttribute(Autograph.VERIFICATION_IMAGE_SIGNATURE + "verificationImageCode");
        return code.equals(realCode);
    }

    /**
     * 修改密码
     *
     * @param user 用户对象
     * @return 结果
     */
    @PostMapping("/changePassword")
    @ResponseBody
    public ResponseVO changePassword(@RequestBody User user) {
        String email = user.getEmail();
        String oldPassword = user.getOldPassword();
        String newPassword = user.getNewPassword();
        boolean changePasswordFlag = accountService.changePasswordByEmailAndOldPassword(email, oldPassword, newPassword);
        Integer code = changePasswordFlag ? StatusCode.CHANGE_PASSWORD_OK : StatusCode.CHANGE_PASSWORD_ERR;
        String msg = changePasswordFlag ? StatusMessage.CHANGE_PASSWORD_OK : StatusMessage.CHANGE_PASSWORD_ERR;
        return new ResponseVO(code, msg);
    }

    @PostMapping("/existEmailAndUsername")
    @ResponseBody
    public ResponseVO existEmailAndUsername(@RequestBody User user) {
        String email = user.getEmail();
        String username = user.getUsername();
        if (accountService.existUsername(username) && accountService.existEmail(email)) {
            return new ResponseVO(StatusCode.EXIST_EMAIL_AND_USERNAME_TRUE, StatusMessage.EXIST_EMAIL_AND_USERNAME_TRUE);
        }
        if (!accountService.existUsername(username) && !accountService.existEmail(email)) {
            return new ResponseVO(StatusCode.EXIST_EMAIL_AND_USERNAME_FALSE, StatusMessage.EXIST_FALSE);
        }
        if (accountService.existUsername(username) && !accountService.existEmail(email)) {
            return new ResponseVO(StatusCode.EXIST_EMAIL_ONLY_TRUE,StatusMessage.EXIST_EMAIL_TRUE);
        }
        return new ResponseVO(StatusCode.EXIST_USERNAME_ONLY_TRUE,StatusMessage.EXIST_USERNAME_TRUE);
    }

}
