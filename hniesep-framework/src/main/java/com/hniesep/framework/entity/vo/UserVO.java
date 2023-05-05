package com.hniesep.framework.entity.vo;

import com.hniesep.framework.entity.Account;
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
    private Account account;
    public UserVO(Account account) {
        this.account = account;
    }

}
