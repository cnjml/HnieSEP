package com.hniesep.base.controller;

import com.hniesep.base.common.*;
import com.hniesep.base.entity.User;
import com.hniesep.base.signinup.service.impl.LoginServiceImpl;
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

    private LoginServiceImpl service;

    /**
     * json注册
     * @param user 请求体：一个json对象
     * @return 返回类
     */
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User user) {
        boolean flag = service.login(user.getUsername(),user.getPassword());
        Integer code = flag ? StatusCode.LOGIN_OK:StatusCode.LOGIN_ERR;
        String msg = flag ? Msg.LOGIN_OK:Msg.LOGIN_ERR;
        return new Result(code,msg);
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
        boolean flag = service.login(username,password);
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
        boolean flag =service.selectByName(user.getRegUsername());
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
        boolean flag =service.selectByName(user.getRegUsername());
        Integer code = !flag ? StatusCode.REGISTER_OK:StatusCode.REGISTER_ERR;
        String msg = !flag ? Msg.REGISTER_OK:Msg.REGISTER_ERR;
        if(!flag) {
            service.register(user.getRegUsername(),user.getRegPwd());
        }
        return new Result(code,msg);
    }

    /**
     * 原始表单的注册
     * @param username 原始表单的username
     * @param password 原始表单的password
     * @return 返回类
     */
    @PostMapping("/originregister")
    @ResponseBody
    @Deprecated
    public Result originregister(@Param("username")String username,@Param("password")String password){
        boolean flag =service.selectByName(username);
        Integer code = !flag ? StatusCode.REGISTER_OK:StatusCode.REGISTER_ERR;
        String msg = !flag ? Msg.REGISTER_OK:Msg.REGISTER_ERR;
        if(!flag) {
            service.register(username,password);
        }
        return new Result(code,msg);
    }

    /**
     * 获取所有用户，json格式
     * @return json对象
     */
    @RequestMapping("/selectall")
    @ResponseBody
    public String selectall(){
        return service.selectAll().toString();
    }

    /**
     * 自动装配
     * @param service 自动装配的userServiceImpl的Bean
     */
    @Autowired
    public void setService(LoginServiceImpl service) {
        this.service = service;
    }

}
