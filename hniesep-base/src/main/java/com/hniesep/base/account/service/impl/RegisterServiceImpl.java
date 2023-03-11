package com.hniesep.base.account.service.impl;

import com.hniesep.base.account.mapper.UserMapper;
import com.hniesep.base.account.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 吉铭炼
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    private UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Override
    public void register(String username, String password, Date regTime) {
        userMapper.insert(username, password,regTime);
    }
}
