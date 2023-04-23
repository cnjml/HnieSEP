package com.hniesep.framework.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * (Account)表实体类
 *
 * @author makejava
 * @since 2023-04-23 11:21:11
 */
@TableName("t_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    /**
     * 账户ID
     */
    @TableId
    private Integer accountId;
    /**
     * 手机号
     */
    private String accountTelephone;
    /**
     * 邮箱
     */
    private String accountEmail;
    /**
     * 用户名
     */
    private String accountUsername;
    /**
     * 密码
     */
    private String accountPassword;
    /**
     * 昵称
     */
    private String accountNickName;
    /**
     * 头像url
     */
    private String accountAvatar;
    /**
     * 注册时间
     */
    private Date accountRegisterTime;
    /**
     * 最后登录时间
     */
    private Date accountLastLoginTime;
    /**
     * 登录IP
     */
    private String accountLastLoginIp;
    /**
     * IP对应地址
     */
    private String accountLoginAddress;
    /**
     * 1:正常 0:禁用
     */
    private Integer accountStatus;
    /**
     * 0:管理员 1:普通用户
     */
    private Integer accountType;

}

