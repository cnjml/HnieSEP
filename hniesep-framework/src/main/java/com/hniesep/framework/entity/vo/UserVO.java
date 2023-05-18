package com.hniesep.framework.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author 吉铭炼
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable{

    private String token;
    private UserInfoVO userInfoVO;
    public UserVO(UserInfoVO userInfoVO) {
        this.userInfoVO = userInfoVO;
    }

}
