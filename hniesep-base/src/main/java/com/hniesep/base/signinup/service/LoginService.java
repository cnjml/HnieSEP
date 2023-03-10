package com.hniesep.base.signinup.service;

import com.hniesep.base.entity.User;

import java.util.Date;
import java.util.List;

/**
 * @author HKRR
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
   *注册
   * @param username 用户名
   * @param password 密码
   * @param regTime  注册时间
   */
  void register(String username, String password, Date regTime);

  /**
   *根据用户名查询用户
   * @param username 用户名
   * @return boolean 返回一个user对象
   */
  boolean selectByName(String username);

  /**
   * 选择所有用户
   * @return 返回所有user对象
   */
  List<User> selectAll();

}
