package com.hniesep.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hniesep.framework.entity.Account;
import com.hniesep.framework.entity.dto.UserDTO;
import com.hniesep.framework.entity.vo.UserVO;
import com.hniesep.framework.exception.SystemException;
import com.hniesep.framework.mapper.AccountMapper;
import com.hniesep.framework.protocol.HttpResultEnum;
import com.hniesep.framework.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Objects;

/**
 * @author 吉铭炼
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private AccountMapper accountMapper;
    @Autowired
    public void setAccountMapper(AccountMapper accountMapper){
        this.accountMapper = accountMapper;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<Account> accountLambdaQueryWrapper = new LambdaQueryWrapper<>();
        accountLambdaQueryWrapper.eq(Account::getAccountUsername,username);
        Account account = accountMapper.selectOne(accountLambdaQueryWrapper);
        if(Objects.isNull(account)){
            throw new SystemException(HttpResultEnum.USER_NOT_EXIST);
        }
        UserDTO userDTO = BeanUtil.copyBean(account,UserDTO.class);
        return new UserVO(account);
    }
}
