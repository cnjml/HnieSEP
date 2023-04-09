package com.hniesep.base.entity;

import lombok.Data;

/**
 * @author 吉铭炼
 */
@Data
public class User {
    private int id;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
    private String verificationCode;
    private String oldPassword;
    private String newPassword;
    private String tel;
}