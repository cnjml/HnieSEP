package com.hniesep.framework.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 吉铭炼
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    /**
     * 账户ID
     */
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
}
