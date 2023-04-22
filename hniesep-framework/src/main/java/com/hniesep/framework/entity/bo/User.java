package com.hniesep.framework.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 吉铭炼
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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