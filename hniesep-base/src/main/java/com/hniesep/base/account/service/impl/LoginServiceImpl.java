package com.hniesep.base.account.service.impl;

import com.hniesep.base.account.service.LoginService;
import com.hniesep.base.account.mapper.UserMapper;
import com.hniesep.base.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author HKRR
 */
@Service
public class LoginServiceImpl implements LoginService {
    UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Override
    public boolean login(String username, String password) {
        return userMapper.select(username, password)!=null;
    }
    @Override
    public boolean selectByName(String username) {
        return userMapper.selectByName(username)!=null;
    }
    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}