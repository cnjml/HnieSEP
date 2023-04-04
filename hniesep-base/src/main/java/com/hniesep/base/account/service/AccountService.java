package com.hniesep.base.account.service;

import com.hniesep.base.entity.User;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

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
    /**
     * 选择所有用户
     * @return 返回所有user对象
     */
    List<User> selectAll();

    /**
     * 设置图片验证码
     * @param rightCode 正确验证码
     * @param httpServletResponse http应答
     */
    void setVerificationImage(String rightCode, HttpServletResponse httpServletResponse);

    /**
     * 使用旧密码更改密码
     * @return 更改密码结果
     * @param account 账户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    boolean changePasswordByOldPassword(String account, String oldPassword,String newPassword);
}