package com.hniesep.base.protocol;

/**
 * @author 吉铭炼
 * 一些返回的状态码
 */
public class StatusCode {
    public static final Integer OK = 200;
    public static final Integer LOGIN_OK = 200;
    public static final Integer EXIST_FALSE = 200;
    public static final Integer SEND_VERIFICATION_CODE_OK = 200;
    public static final Integer CHECK_VERIFICATION_CODE_OK = 200;
    public static final Integer REGISTER_OK = 200;
    public static final Integer VERIFICATION_CODE_EXIST = 200;
    public static final Integer EMAIL_LEGITIMATE = 200;
    public static final Integer CHANGE_PASSWORD_OK = 200;
    public static final Integer EXIST_EMAIL_AND_USERNAME_FALSE = 200;
    public static final Integer EXIST_EMAIL_ONLY_FALSE = 200;
    public static final Integer EXIST_USERNAME_ONLY_FALSE = 200;




    public static final Integer LOGIN_ERR = 4001;
    public static final Integer EXIST_EMAIL_ONLY_TRUE = 4002;
    public static final Integer EXIST_USERNAME_ONLY_TRUE = 4003;
    public static final Integer SEND_VERIFICATION_CODE_ERR = 4004;
    public static final Integer CHECK_VERIFICATION_CODE_ERR = 4005;
    public static final Integer REGISTER_ERR = 4006;
    public static final Integer VERIFICATION_CODE_EMPTY = 4007;
    public static final Integer EMAIL_ILLEGAL = 4008;
    public static final Integer CHANGE_PASSWORD_ERR = 4009;
    public static final Integer EXIST_TRUE = 4010;
    public static final Integer EXIST_EMAIL_AND_USERNAME_TRUE = 4011;

}