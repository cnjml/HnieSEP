package com.hniesep.user.controller;

import com.hniesep.framework.annotation.SystemLog;
import com.hniesep.framework.entity.Account;
import com.hniesep.framework.entity.bo.UserBO;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.vo.UserInfoVO;
import com.hniesep.framework.entity.vo.UserVO;
import com.hniesep.framework.protocol.Signature;
import com.hniesep.framework.service.impl.AccountServiceImpl;
import com.hniesep.framework.service.impl.LoginServiceImpl;
import com.hniesep.framework.service.impl.RegisterServiceImpl;
import com.hniesep.framework.util.VerificationUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 登录
     * @param userBO 用户业务对象
     * @return 响应结果
     */
    @PostMapping("/login")
    @ResponseBody
    @SystemLog(businessName = "登录")
    public ResponseResult<UserVO> login(@RequestBody UserBO userBO){
        return loginService.login(userBO);
    }
    /**
     * 退出登录
     * @return 响应结果
     */
    @GetMapping("/logout")
    @ResponseBody
    public ResponseResult<Object> logout(){
        return loginService.logout();
    }
    /**
     * 检测用户名是否注册
     * @param username 待检测用户名
     * @return 用户名是否注册
     */
    @GetMapping("/existUsername/{username}")
    @ResponseBody
    public ResponseResult<Object> existUsername(@PathVariable("username") String username) {
        return accountService.existUsername(username);
    }
    /**
     * 检测邮箱是否注册
     * @param email 待检测邮箱
     * @return 邮箱是否注册
     */
    @GetMapping("/existEmail/{email}")
    @ResponseBody
    public ResponseResult<Object> existEmail(@PathVariable("email")String email) {
        return accountService.existEmail(email);
    }
    /**
     * json注册
     * @param userBO 请求体：一个json对象
     * @return 返回类
     */
    @PostMapping("/register")
    @ResponseBody
    @SystemLog(businessName = "注册")
    public ResponseResult<Object> register(@RequestBody UserBO userBO) {
        return registerService.register(userBO);
    }
    /**
     * 获取所有用户，json格式
     * @return 返回类
     */
    @GetMapping("/selectAll")
    @ResponseBody
    public ResponseResult<List<Account>> selectAll(){
        return accountService.selectAll();
    }
    /**
     * 获取图片验证码
     */
    @GetMapping("/getVerificationImage")
    @ResponseBody
    public void verificationImage(HttpServletResponse httpServletResponse){
        String rightCode = VerificationUtil.generateVerificationCode();
        httpServletResponse.setHeader(Signature.VERIFICATION_IMAGE_SIGNATURE + "verificationImageCode", rightCode);
        httpServletResponse.setContentType("image/jpeg");
        httpServletResponse.setHeader("Cache-Control","no-cache");
        accountService.setVerificationImage(rightCode,httpServletResponse);
    }
    /**
     * 校验图片验证码
     */
    @GetMapping("/checkVerificationImage/{VerificationCode}")
    @ResponseBody
    public boolean checkVerificationImage(@PathVariable("VerificationCode") String code, HttpSession httpSession) {
        String realCode = (String) httpSession.getAttribute(Signature.VERIFICATION_IMAGE_SIGNATURE + "verificationImageCode");
        return code.equals(realCode);
    }
    /**
     * 修改密码
     * @param userBO 用户对象
     * @return 结果
     */
    @PutMapping("/changePassword")
    @ResponseBody
    public ResponseResult<Object> changePassword(@RequestBody UserBO userBO) {
        return accountService.changePasswordByEmailAndOldPassword(userBO);
    }
    @GetMapping("/userInfo/{accountId}")
    @ResponseBody
    public ResponseResult<UserInfoVO> useInfo(@PathVariable("accountId")Long accountId) {
        return accountService.getUserInfo(accountId);
    }

}
