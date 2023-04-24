package com.hniesep.framework.protocol;

/**
 * @author 吉铭炼
 */

public enum HttpResultEnum {
    /**
     * 相应结果枚举类
     */
    SUCCESS(200, "操作成功"),
    FAILED(400,"操作失败"),
    NEED_LOGIN(401, "需要登录后操作"),
    NO_PERMISSION(403, "无操作权限"),
    NO_INTERFACE(404, "接口不存在"),
    SYSTEM_ERROR(500, "系统错误"),
    USER_NOT_EXIST(446,"用户不存在"),

    CREDENTIALS_EXPIRE(445,"登录过期，请重新登录"),
    ARGUMENTS_ERROR(444,"参数错误"),
    USERNAME_EXIT(501, "用户名已存在"),
    PHONE_EXIST(502, "手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    NICKNAME_EXIST(504, "该昵称已经存在"),
    REQUIRE_USERNAME(505, "必需填写用户名"),
    FILE_TYPE_ERROR(506, "文件类型错误"),
    PASSWORD_NOT_NULL(507, "密码不能为空"),
    EMAIL_NOT_NULL(508, "邮箱不能为空"),
    VERIFICATION_CODE_EXIST(411,"验证码还在有效期内"),
    LOGIN_ERROR(509, "用户名或密码错误");

    final String msg;
    final Integer code;
    HttpResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
