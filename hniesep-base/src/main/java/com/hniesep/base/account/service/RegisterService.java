package com.hniesep.base.account.service;

import java.util.Date;

/**
 * @author 吉铭炼
 */
public interface RegisterService {
    /**
     *注册
     * @param username 用户名
     * @param password 密码
     * @param regTime  注册时间
     * @param email 注册邮箱
     */
    void register(String username, String password,String email, Date regTime);
    /**
     *设置注册验证码
     * @param toAddress 待注册的邮箱
     */
    void setRegisterVerificationCode(String toAddress);
    /**
     * 校验验证码
     * @param verificationCode 验证码
     * @param username 用户名
     * @return 验证码校验结果
     */
    boolean checkRegisterVerificationCode(String username, String verificationCode);
    /**
     * 获取待注册用户名的验证码
     * @param username 待注册用户名
     * @return 待注册用户名的验证码
     */
    String getRegisterVerificationCode(String username);
}