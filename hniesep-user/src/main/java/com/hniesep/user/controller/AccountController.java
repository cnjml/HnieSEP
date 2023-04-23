package com.hniesep.user.controller;

import com.hniesep.framework.entity.bo.UserBO;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.vo.UserVO;
import com.hniesep.framework.exception.ServiceException;
import com.hniesep.framework.protocol.Autograph;
import com.hniesep.framework.protocol.StatusCode;
import com.hniesep.framework.protocol.StatusMessage;
import com.hniesep.framework.service.impl.AccountServiceImpl;
import com.hniesep.framework.service.impl.LoginServiceImpl;
import com.hniesep.framework.service.impl.RegisterServiceImpl;
import com.hniesep.framework.util.DateTimeUtil;
import com.hniesep.framework.util.StringUtil;
import com.hniesep.framework.util.VerificationUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 吉铭炼
 * user控制器
 */
@RestController
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
     * @param userBO 请求体：一个json对象
     * @return 返回类
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseResult login(@RequestBody UserBO userBO) {
        boolean flag = loginService.login(userBO.getEmail(), userBO.getPassword()) || loginService.login(userBO.getUsername(), userBO.getPassword());
        Integer code = flag ? StatusCode.LOGIN_OK : StatusCode.LOGIN_ERR;
        String msg = flag ? StatusMessage.LOGIN_OK : StatusMessage.LOGIN_ERR;
        return new ResponseResult(code, msg);
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
    public ResponseResult originLogin(@Param("username") String username, @Param("email") String email, @Param("password") String password) {
        boolean flag = loginService.login(username, password) || loginService.login(email, password);
        Integer code = flag ? StatusCode.LOGIN_OK : StatusCode.LOGIN_ERR;
        String msg = flag ? StatusMessage.LOGIN_OK : StatusMessage.LOGIN_ERR;
        return new ResponseResult(code, msg);
    }

    /**
     * json注册
     *
     * @param userBO 请求体：一个json对象
     * @return 返回类
     */
    @PostMapping("/isExist")
    @ResponseBody
    public ResponseResult isExist(@RequestBody UserBO userBO) {
        boolean existFlag = accountService.exist(userBO.getUsername(), userBO.getEmail());
        Integer code = existFlag ? StatusCode.EXIST_TRUE : StatusCode.EXIST_FALSE;
        String msg = existFlag ? StatusMessage.EXIST_TRUE : StatusMessage.EXIST_FALSE;
        return new ResponseResult(code, msg);
    }

    /**
     * 检测用户名是否注册
     *
     * @param userBO 待检测用户对象
     * @return 用户名是否注册
     */
    @PostMapping("/existUsername")
    @ResponseBody
    public ResponseResult existUsername(@RequestBody UserBO userBO) {
        boolean existFlag = accountService.existUsername(userBO.getUsername());
        Integer code = existFlag ? StatusCode.EXIST_USERNAME_ONLY_TRUE : StatusCode.EXIST_USERNAME_ONLY_FALSE;
        String msg = existFlag ? StatusMessage.EXIST_USERNAME_TRUE : StatusMessage.EXIST_USERNAME_FALSE;
        return new ResponseResult(code, msg);
    }

    /**
     * 检测邮箱是否注册
     *
     * @param userBO 待检测用户对象
     * @return 邮箱是否注册
     */
    @PostMapping("/existEmail")
    @ResponseBody
    public ResponseResult existEmail(@RequestBody UserBO userBO) {
        boolean existFlag = accountService.existEmail(userBO.getEmail());
        Integer code = existFlag ? StatusCode.EXIST_EMAIL_ONLY_TRUE : StatusCode.EXIST_EMAIL_ONLY_FALSE;
        String msg = existFlag ? StatusMessage.EXIST_EMAIL_TRUE : StatusMessage.EXIST_EMAIL_FALSE;
        return new ResponseResult(code, msg);
    }

    /**
     * json注册
     *
     * @param userBO 请求体：一个json对象
     * @return 返回类
     */
    @PostMapping("/register")
    @ResponseBody
    public ResponseResult register(@RequestBody UserBO userBO) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String email = userBO.getEmail();
        if (!StringUtil.validPassword(password)) {
            throw new ServiceException("密码格式错误");
        }
        if (!StringUtil.validUsername(username)) {
            throw new ServiceException("用户名格式错误");
        }
        if (!StringUtil.validEmail(email)) {
            throw new ServiceException("邮箱格式错误");
        }
        //判断验证码是否为空
        boolean emptyVerificationCodeFlag = userBO.getVerificationCode() == null || "".equals(userBO.getVerificationCode());
        Integer code = emptyVerificationCodeFlag ? StatusCode.VERIFICATION_CODE_EMPTY : StatusCode.VERIFICATION_CODE_EXIST;
        String msg = emptyVerificationCodeFlag ? StatusMessage.VERIFICATION_CODE_EMPTY : StatusMessage.VERIFICATION_CODE_EXIST;
        if (emptyVerificationCodeFlag) {
            return new ResponseResult(code, msg);
        }
        //判断用户名和邮箱是否存在
        boolean existFlag = accountService.exist(username, email);
        code = existFlag ? StatusCode.EXIST_TRUE : StatusCode.EXIST_FALSE;
        msg = existFlag ? StatusMessage.EXIST_TRUE: StatusMessage.EXIST_FALSE;

        //用户名和邮箱不存在则进行下一步注册
        if(!existFlag) {
            //校验验证码
            boolean checkVerificationCodeFlag = registerService.checkRegisterVerificationCode(email, userBO.getVerificationCode());
            code = checkVerificationCodeFlag ? StatusCode.CHECK_VERIFICATION_CODE_OK : StatusCode.CHECK_VERIFICATION_CODE_ERR;
            msg = checkVerificationCodeFlag ? StatusMessage.CHECK_VERIFICATION_CODE_OK : StatusMessage.CHECK_VERIFICATION_CODE_ERR;
            //校验成功则直接注册
            if(checkVerificationCodeFlag){
                boolean registerFlag = registerService.register(email,username,password,DateTimeUtil.getDateTime())>=1;
                code = registerFlag ? StatusCode.REGISTER_OK:StatusCode.REGISTER_ERR;
                msg = registerFlag ? StatusMessage.REGISTER_OK:StatusMessage.REGISTER_ERR;
            }
        }
        return new ResponseResult(code,msg);
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
     * @param userBO 用户对象
     * @return 结果
     */
    @PostMapping("/changePassword")
    @ResponseBody
    public ResponseResult changePassword(@RequestBody UserBO userBO) {
        String email = userBO.getEmail();
        String oldPassword = userBO.getOldPassword();
        String newPassword = userBO.getNewPassword();
        boolean changePasswordFlag = accountService.changePasswordByEmailAndOldPassword(email, oldPassword, newPassword);
        Integer code = changePasswordFlag ? StatusCode.CHANGE_PASSWORD_OK : StatusCode.CHANGE_PASSWORD_ERR;
        String msg = changePasswordFlag ? StatusMessage.CHANGE_PASSWORD_OK : StatusMessage.CHANGE_PASSWORD_ERR;
        return new ResponseResult(code, msg);
    }

    @PostMapping("/existEmailAndUsername")
    @ResponseBody
    public ResponseResult existEmailAndUsername(@RequestBody UserBO userBO) {
        String email = userBO.getEmail();
        String username = userBO.getUsername();
        if (accountService.existUsername(username) && accountService.existEmail(email)) {
            return new ResponseResult(StatusCode.EXIST_EMAIL_AND_USERNAME_TRUE, StatusMessage.EXIST_EMAIL_AND_USERNAME_TRUE);
        }
        if (!accountService.existUsername(username) && !accountService.existEmail(email)) {
            return new ResponseResult(StatusCode.EXIST_EMAIL_AND_USERNAME_FALSE, StatusMessage.EXIST_FALSE);
        }
        if (accountService.existUsername(username) && !accountService.existEmail(email)) {
            return new ResponseResult(StatusCode.EXIST_EMAIL_ONLY_TRUE,StatusMessage.EXIST_EMAIL_TRUE);
        }
        return new ResponseResult(StatusCode.EXIST_USERNAME_ONLY_TRUE,StatusMessage.EXIST_USERNAME_TRUE);
    }
    @PostMapping("/authLogin")
    @ResponseBody
    public ResponseResult<UserVO> authLogin(@RequestBody UserBO userBO){
        return loginService.authLogin(userBO);
    }

}
