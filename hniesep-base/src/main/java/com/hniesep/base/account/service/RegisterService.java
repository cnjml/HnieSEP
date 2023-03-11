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
     */
    void register(String username, String password, Date regTime);
}