package com.hniesep.base.account.service;

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

}