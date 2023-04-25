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
 * @since 2023-04-25 15:52:32
 */
@TableName("t_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    /**
     * 账户ID notnull 主键自增
     */
    @TableId
    private Long accountId;
    /**
     * 手机号
     */
    private String accountTelephone;
    /**
     * 邮箱 notnull
     */
    private String accountEmail;
    /**
     * 用户名 notnull
     */
    private String accountUsername;
    /**
     * 密码 notnull
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
     * 账户状态 0:禁用 1:正常 默认1
     */
    private Integer accountStatus;
    /**
     * 账户权限 0:管理员 1:用户 默认1
     */
    private Integer accountType;

}
