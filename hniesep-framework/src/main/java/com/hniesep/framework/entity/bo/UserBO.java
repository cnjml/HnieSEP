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
public class UserBO {
    private String username;
    private String password;
    private String email;
    private String verificationCode;
    private String oldPassword;
    private String newPassword;
    private String nickname;
}