package com.hniesep.framework.protocol;

/**
 * @author 吉铭炼
 */

public enum HttpResultEnum {
    /**
     * 响应结果枚举类
     */
    NEED_LOGIN(401, "需要登录后操作"),
    NO_PERMISSION(403, "无操作权限"),
    NO_INTERFACE(404, "接口不存在"),
    SYSTEM_ERROR(500, "系统错误"),




    USER_NOT_EXIST(445,"用户不存在"),
    CREDENTIALS_EXPIRE(446,"登录过期，请重新登录"),
    PHONE_EXIST(447, "手机号已存在"),
    EMAIL_EXIST(448, "邮箱已存在"),
    USERNAME_EXIST(449, "该用户名已经存在"),
    FILE_TYPE_ERROR(450, "文件类型错误"),
    VERIFICATION_CODE_EXIST(451,"验证码还在有效期内"),
    LOGIN_ERROR(452, "用户名或密码错误"),
    CONTENT_IS_NULL(453,"内容为空"),
    VERIFICATION_CODE_ERROR(454,"验证码错误"),
    ARTICLE_ALREADY_LIKE(455,"文章已点赞"),
    ARTICLE_UN_LIKE(200,"文章未点赞"),
    COMMENT_ALREADY_LIKE(456,"评论已经点赞"),
    COMMENT_UN_LIKE(200,"评论未点赞"),
    ARTICLE_UN_FAVORITE(200,"文章未收藏"),
    ARTICLE_ALREADY_FAVORITE(457,"文章已收藏"),




    ARGUMENTS_ERROR(444,"参数错误"),
    SUCCESS(200, "操作成功"),
    FAILED(400,"操作失败");
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
