package com.hniesep.base.controller;

import com.hniesep.base.account.service.impl.RegisterServiceImpl;
import com.hniesep.base.protocol.*;
import com.hniesep.base.entity.Result;
import com.hniesep.base.entity.User;
import com.hniesep.base.account.service.impl.LoginServiceImpl;
import com.hniesep.base.util.DateTimeUtil;
import com.hniesep.base.account.service.impl.AccountServiceImpl;
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
    public void setAccountService(AccountServiceImpl accountService){
        this.accountService = accountService;
    }
    /**
     * json注册
     * @param user 请求体：一个json对象
     * @return 返回类
     */
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User user) {
        boolean flag = loginService.login(user.getUsername(),user.getPassword());
        Integer code = flag ? StatusCode.LOGIN_OK:StatusCode.LOGIN_ERR;
        String msg = flag ? StatusMessage.LOGIN_OK: StatusMessage.LOGIN_ERR;
        return new Result(code,msg);
    }
    /**
     * 原始表单登录
     * @param username 原始表单的username
     * @param password 原始表单的password
     * @return 返回类
     */
    @RequestMapping("/originLogin")
    @ResponseBody
    @Deprecated
    public Result originLogin(@Param("username")String username,@Param("password")String password){
        boolean flag = loginService.login(username,password);
        Integer code = flag ? StatusCode.LOGIN_OK:StatusCode.LOGIN_ERR;
        String msg = flag ? StatusMessage.LOGIN_OK: StatusMessage.LOGIN_ERR;
        return new Result(code,msg);
    }
    /**
     * json注册
     * @param user 请求体：一个json对象
     * @return 返回类
     */
    @RequestMapping("/isRegister")
    @ResponseBody
    public Result isRegister(@RequestBody User user){
        boolean existFlag = accountService.checkExist(user.getRegUsername(),user.getEmail());
        Integer code = existFlag ? StatusCode.EXIST_TRUE:StatusCode.EXIST_FALSE;
        String msg = existFlag ? StatusMessage.EXIST_TRUE: StatusMessage.EXIST_FALSE;
        return new Result(code,msg);
    }
    /**
     * json注册
     * @param user 请求体：一个json对象
     * @return 返回类
     */
    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestBody User user){
        //判断验证码是否为空
        boolean emptyVerificationCodeFlag = user.getVerificationCode()==null|| "".equals(user.getVerificationCode());
        Integer code = emptyVerificationCodeFlag ? StatusCode.VERIFICATION_CODE_EMPTY:StatusCode.VERIFICATION_CODE_EXIST;
        String msg = emptyVerificationCodeFlag ? StatusMessage.VERIFICATION_CODE_EMPTY: StatusMessage.VERIFICATION_CODE_EXIST;
        if (emptyVerificationCodeFlag){
            return new Result(code,msg);
        }
        //判断用户名是否存在
        boolean existFlag = accountService.checkExist(user.getRegUsername(),user.getEmail());
        code = existFlag ? StatusCode.EXIST_TRUE:StatusCode.EXIST_FALSE;
        msg = existFlag ? StatusMessage.EXIST_TRUE: StatusMessage.EXIST_FALSE;
        //用户名不存在则进行下一步注册
        if(!existFlag) {
            //校验验证码
            boolean checkVerificationCodeFlag = registerService.checkRegisterVerificationCode(user.getEmail(),user.getVerificationCode());
            code = checkVerificationCodeFlag ? StatusCode.CHECK_VERIFICATION_CODE_OK : StatusCode.CHECK_VERIFICATION_CODE_ERR;
            msg = checkVerificationCodeFlag ? StatusMessage.CHECK_VERIFICATION_CODE_OK : StatusMessage.CHECK_VERIFICATION_CODE_ERR;
            //校验成功则直接注册
            if(checkVerificationCodeFlag){
                registerService.register(user.getRegUsername(),user.getRegPwd(),user.getEmail(),DateTimeUtil.getDateTime());
                code = StatusCode.REGISTER_OK;
                msg = StatusMessage.REGISTER_OK;
            }
        }
        return new Result(code,msg);
    }
    /**
     * 原始表单的注册
     * @param username 原始表单的username
     * @param password 原始表单的password
     * @return 返回类
     */
    @PostMapping("/originRegister")
    @ResponseBody
    @Deprecated
    public Result originRegister(@Param("username")String username,@Param("password")String password,@Param("email")String email){
        boolean flag = accountService.checkExist(username,email);
        Integer code = !flag ? StatusCode.REGISTER_OK:StatusCode.REGISTER_ERR;
        String msg = !flag ? StatusMessage.REGISTER_OK: StatusMessage.REGISTER_ERR;
        if(!flag) {
            registerService.register(username,password,email,DateTimeUtil.getDateTime());
        }
        return new Result(code,msg);
    }
    /**
     * 获取所有用户，json格式
     * @return json对象
     */
    @RequestMapping("/selectAll")
    @ResponseBody
    public String selectAll(){
        return accountService.selectAll().toString();
    }
    /**
     * 获取图片验证码
     */
    @RequestMapping("/setVerificationImage")
    @ResponseBody
    public void verificationImage(HttpServletResponse httpServletResponse){
        String realCode = VerificationUtil.generateVerificationCode();
        httpServletResponse.setHeader(Autograph.VERIFICATION_IMAGE_SIGNATURE + "verificationImageCode", realCode);
        httpServletResponse.setContentType("image/jpeg");
        httpServletResponse.setHeader("Cache-Control","no-cache");
        VerificationUtil.generateVerificationImage(realCode,httpServletResponse);
    }
    /**
     * 校验图片验证码
     */
    @RequestMapping("/checkVerificationImage")
    @ResponseBody
    public boolean checkVerificationImage(@Param("code") String code, HttpSession httpSession){
        System.out.println(code);
        String realCode = (String) httpSession.getAttribute(Autograph.VERIFICATION_IMAGE_SIGNATURE + "verificationImageCode");
        return code.equals(realCode);
    }
}