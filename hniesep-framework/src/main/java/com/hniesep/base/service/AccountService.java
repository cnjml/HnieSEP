package com.hniesep.base.service;

import com.hniesep.base.entity.bo.User;
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
    boolean exist(String username,String email);
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
     * 使用邮箱以及旧密码更改密码
     * @return 更改密码结果
     * @param email 邮箱
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    boolean changePasswordByEmailAndOldPassword(String email, String oldPassword,String newPassword);
    /**
     * 用户名是否注册
     * @return 用户名是否注册
     * @param username 用户名
     */
    boolean existUsername(String username);
    /**
     * 邮箱是否注册
     * @return 邮箱是否注册
     * @param email 邮箱
     */
    boolean existEmail(String email);

}