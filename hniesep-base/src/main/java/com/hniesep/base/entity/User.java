package com.hniesep.base.entity;

import lombok.Data;

/**
 * @author 吉铭炼
 */
@Data
public class User {
    private int id;
    private String username;
    private String regUsername;
    private String regPwd;
    private String password;
    private String tel;
    private String email;
    private String verificationCode;
}