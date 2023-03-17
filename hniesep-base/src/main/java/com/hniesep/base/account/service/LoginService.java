package com.hniesep.base.account.service;

import java.util.List;

import com.hniesep.base.entity.User;
/**
 * @author 吉铭炼
 */
public interface LoginService {
  /**
   *登录
   * @param username 用户名
   * @param password 密码
   * @return 返回一个对象
   */
  boolean login(String username, String password);
  /**
   * 选择所有用户
   * @return 返回所有user对象
   */
  List<User> selectAll();
}