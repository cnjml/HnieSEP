package com.hniesep.base.protocol;

/**
 * @author 吉铭炼
 * 一些返回的信息
 */
public class StatusMessage {
    public static final String LOGIN_OK = "登录成功";
    public static final String EXIST_FALSE = "用户名可用";
    public static final String SEND_VERIFICATION_CODE_OK = "验证码发送成功";
    public static final String CHECK_VERIFICATION_CODE_OK = "验证码校验成功";
    public static final String REGISTER_OK = "注册成功";
    public static final String VERIFICATION_CODE_EXIST = "验证码非空";
    public static final String EMAIL_LEGITIMATE = "邮箱格式合法";
    public static final String CHANGE_PASSWORD_OK = "更改密码成功";


    public static final String LOGIN_ERR = "用户名或密码错误";
    public static final String EXIST_TRUE = "用户名或邮箱已存在";
    public static final String SEND_VERIFICATION_CODE_ERR = "发送验证码失败";
    public static final String CHECK_VERIFICATION_CODE_ERR = "验证码校验失败";
    public static final String REGISTER_ERR = "注册失败";
    public static final String VERIFICATION_CODE_EMPTY = "验证码为空";
    public static final String EMAIL_ILLEGAL = "邮箱格式错误";
    public static final String CHANGE_PASSWORD_ERR = "原用户名或密码错误";
}