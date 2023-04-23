package com.hniesep.framework.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hniesep.framework.entity.Account;
import com.hniesep.framework.entity.bo.UserBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Account)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-20 20:02:31
 */
@Mapper
@DS("user")
public interface AccountMapper extends BaseMapper<Account> {
    /**
     *查询所有用户
     * @return 返回所有用户
     */
    List<UserBO> selectAll();
    /**
     * 登录用户名密码校验
     * @param username 用户名
     * @param password 密码
     * @return 查询用户
     */
    UserBO loginByName(@Param("username") String username, @Param("password") String password);
    /**
     * 登录邮箱密码校验
     * @param email 用户名
     * @param password 密码
     * @return 查询用户
     */
    UserBO loginByEmail(@Param("email") String email, @Param("password") String password);
    /**
     *根据邮箱查找用户
     * @param email 用户名
     * @return 返回一个用户
     *
     */
    UserBO selectByEmail(@Param("email")String email);
    /**
     *根据用户名查找用户
     * @param username 用户名
     * @return 返回一个用户
     *
     */
    UserBO selectByName(@Param("username")String username);
    /**
     * 使用旧密码更改密码
     * @return 更改密码结果
     * @param account 账户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    boolean changePasswordByOldPassword(String account, String oldPassword,String newPassword);

}

