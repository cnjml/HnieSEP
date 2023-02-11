package com.hniesep.base.signinup.service.impl;

import com.hniesep.base.signinup.service.LoginService;
import com.hniesep.base.signinup.mapper.UserMapper;
import com.hniesep.base.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HKRR
 */
@Service
public class LoginServiceImpl implements LoginService {

    final UserMapper userMapper;

    public LoginServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean login(String username, String password) {
        return userMapper.select(username, password)!=null;
    }

    @Override
    public void register(String username, String password) {
        userMapper.insert(username, password);
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
