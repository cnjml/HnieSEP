package com.hniesep.base.controller;

import com.hniesep.base.account.service.impl.RegisterServiceImpl;
import com.hniesep.base.common.*;
import com.hniesep.base.entity.User;
import com.hniesep.base.account.service.impl.LoginServiceImpl;
import com.hniesep.base.util.DateTimeUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 吉铭炼
 * user控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private LoginServiceImpl loginService;
    private RegisterServiceImpl registerService;
    @Autowired
    public void setLoginService(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }
    @Autowired
    public void setRegisterService(RegisterServiceImpl registerService) {
        this.registerService = registerService;
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
        String msg = flag ? Msg.LOGIN_OK:Msg.LOGIN_ERR;
        return new Result(code,msg);
    }

    /**
     *
     * @return 返回类
     */
    @RequestMapping()
    @ResponseBody
    public Result verify(){
        boolean flag = true;
        return new Result();
    }

    /**
     * 原始表单登录
     * @param username 原始表单的username
     * @param password 原始表单的password
     * @return 返回类
     */
    @RequestMapping("/originlogin")
    @ResponseBody
    @Deprecated
    public Result originLogin(@Param("username")String username,@Param("password")String password){
        boolean flag = loginService.login(username,password);
        Integer code = flag ? StatusCode.LOGIN_OK:StatusCode.LOGIN_ERR;
        String msg = flag ? Msg.LOGIN_OK:Msg.LOGIN_ERR;
        return new Result(code,msg);
    }

    /**
     * json注册
     * @param user 请求体：一个json对象
     * @return 返回类
     */
    @RequestMapping("/isreg")
    @ResponseBody
    public Result isreg(@RequestBody User user){
        boolean flag = loginService.selectByName(user.getRegUsername());
        Integer code = !flag ? StatusCode.ISREG_OK:StatusCode.ISREG_ERR;
        String msg = !flag ? Msg.ISREG_OK:Msg.ISREG_ERR;
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
        boolean flag = loginService.selectByName(user.getRegUsername());
        Integer code = !flag ? StatusCode.REGISTER_OK:StatusCode.REGISTER_ERR;
        String msg = !flag ? Msg.REGISTER_OK:Msg.REGISTER_ERR;
        if(!flag) {
            registerService.register(user.getRegUsername(),user.getRegPwd(), DateTimeUtil.getDateTime());
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
    public Result originRegister(@Param("username")String username,@Param("password")String password){
        boolean flag = loginService.selectByName(username);
        Integer code = !flag ? StatusCode.REGISTER_OK:StatusCode.REGISTER_ERR;
        String msg = !flag ? Msg.REGISTER_OK:Msg.REGISTER_ERR;
        if(!flag) {
            registerService.register(username,password, DateTimeUtil.getDateTime());
        }
        return new Result(code,msg);
    }

    /**
     * 获取所有用户，json格式
     * @return json对象
     */
    @RequestMapping("/selectall")
    @ResponseBody
    public String selectAll(){
        return loginService.selectAll().toString();
    }

}