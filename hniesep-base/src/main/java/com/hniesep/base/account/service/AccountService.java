package com.hniesep.base.account.service;

import com.hniesep.base.entity.User;

/**
 * @author 吉铭炼
 */
public interface AccountService {
    /**
     *查询账号是否存在
     * @param username 用户名
     * @param email 邮箱
     * @return 是否存在
     */
    boolean checkExist(String username,String email);
    /**
     *根据用户名查询用户
     * @param username 用户名
     * @return boolean 返回一个user对象
     */
    User selectByName(String username);
    /**
     *根据邮箱查询用户
     * @param email 用户名
     * @return boolean 返回一个user对象
     */
    User selectByEmail(String email);
}