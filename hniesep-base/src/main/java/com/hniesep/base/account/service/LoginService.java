package com.hniesep.base.account.service;

/**
 * @author 吉铭炼
 */
public interface LoginService {
  /**
   *使用用户名登录
   * @param username 用户名
   * @param password 密码
   * @return 是否登录成功
   */
  boolean loginByName(String username, String password);
  /**
   *使用邮箱登录
   * @param email 邮箱
   * @param password 密码
   * @return 是否登录成功
   */
  boolean loginByEmail(String email, String password);
  /**
   *未指定方式登录
   * @param account 用户名或邮箱
   * @param password 密码
   * @return 是否登录成功
   */
  boolean login(String account,String password);
}