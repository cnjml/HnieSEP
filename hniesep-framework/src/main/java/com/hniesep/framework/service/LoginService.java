package com.hniesep.framework.service;

import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.bo.UserBO;
import com.hniesep.framework.entity.vo.UserVO;

/**
 * @author 吉铭炼
 */
public interface LoginService {
    /**
     * 使用邮箱登录
     *
     * @param email    邮箱
     * @param password 密码
     * @return 是否登录成功
     */
    boolean loginByEmail(String email, String password);
    /**
     * 登录，返回用户token及信息
     * @param userBO 用户业务对象
     * @return 响应结果
     */
    ResponseResult<UserVO> login(UserBO userBO);
    /**
     * 退出登录
     * @return 响应结果
     */
    ResponseResult<Object> logout();

}
