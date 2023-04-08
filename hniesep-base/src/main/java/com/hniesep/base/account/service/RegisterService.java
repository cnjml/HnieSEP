package com.hniesep.base.account.service;

import java.util.Date;

/**
 * @author 吉铭炼
 */
public interface RegisterService {
    /**
     * 注册
     *
     * @param username 用户名
     * @param password 密码
     * @param regTime  注册时间
     * @param email    注册邮箱
     * @return 是否注册成功
     */
    boolean register(String email,String username, String password,Date regTime);
    /**
     *设置注册验证码
     * @param toAddress 待注册的邮箱
     * @param verificationCode 验证码
     */
    void setRegisterVerificationCode(String toAddress,String verificationCode);
    /**
     * 校验注册验证码
     * @param verificationCode 验证码
     * @param username 用户名
     * @return 返回校验结果
     */
    boolean checkRegisterVerificationCode(String username, String verificationCode);
    /**
     * 获取待注册验证码
     * @param username 待注册用户名
     * @return 待注册用户名的验证码
     */
    String getRegisterVerificationCode(String username);
}