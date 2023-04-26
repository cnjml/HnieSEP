package com.hniesep.framework.service;

import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.bo.UserBO;


/**
 * @author 吉铭炼
 */
public interface RegisterService {
    /**
     * 注册
     * @param userBO    用户业务对象
     * @return 是否注册成功
     */
    ResponseResult<Object> register(UserBO userBO);
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

}
