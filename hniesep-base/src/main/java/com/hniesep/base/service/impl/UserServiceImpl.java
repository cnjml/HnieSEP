package com.hniesep.base.service.impl;

import com.hniesep.base.mapper.UserMapper;
import com.hniesep.base.entity.User;
import com.hniesep.base.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HKRR
 */
@Service
public class UserServiceImpl implements UserService {

    final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
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
