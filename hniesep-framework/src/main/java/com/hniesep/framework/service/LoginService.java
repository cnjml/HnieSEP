package com.hniesep.framework.service;

import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.bo.UserBO;
import com.hniesep.framework.entity.vo.UserVO;

/**
 * @author 吉铭炼
 */
public interface LoginService {
    /**
     * 使用用户名登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 是否登录成功
     */
    boolean loginByName(String username, String password);

    /**
     * 使用邮箱登录
     *
     * @param email    邮箱
     * @param password 密码
     * @return 是否登录成功
     */
    boolean loginByEmail(String email, String password);

    /**
     * 未指定方式登录
     *
     * @param account  用户名或邮箱
     * @param password 密码
     * @return 是否登录成功
     */
    boolean login(String account, String password);

    /**
     * 返回用户信息
     *
     * @param userBO 用户业务对象
     * @return 返回类
     */
    ResponseResult<UserVO> authLogin(UserBO userBO);

    /**
     * 退出登录
     *
     * @return 响应结果
     */
    ResponseResult<Object> logout();
}