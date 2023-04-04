package com.hniesep.base.account.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

import com.hniesep.base.entity.User;

/**
 * @author 吉铭炼
 */
@Mapper
@DS("user")
public interface AccountMapper {
    /**
     *查询所有用户
     * @return 返回所有用户
     */
    List<User> selectAll();
    /**
     * 根据id查询用户
     * @param id id
     * @return 返回根据id选择的用户
     */
    User selectById(int id);
    /**
     *根据用户名查找用户
     * @param username 用户名
     * @return 返回一个用户
     *
     */
    User selectByName(String username);
    /**
     * 查询账号和密码
     * @param username 用户名
     * @param password 密码
     * @return 查询用户
     */
    User select(@Param("username") String username,@Param("password") String password);
    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @param regTime 注册时间
     * @param email 注册邮箱
     * @return 是否注册成功
     */
    boolean insert(@Param("username")String username,@Param("password")String password,@Param("email")String email,@Param("regTime") Date regTime);
    /**
     *根据邮箱查找用户
     * @param email 用户名
     * @return 返回一个用户
     *
     */
    User selectByEmail(@Param("email")String email);

    /**
     * 使用旧密码更改密码
     * @return 更改密码结果
     * @param account 账户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    boolean changePasswordByOldPassword(String account, String oldPassword,String newPassword);

}