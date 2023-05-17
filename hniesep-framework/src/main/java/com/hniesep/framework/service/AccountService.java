package com.hniesep.framework.service;

import com.hniesep.framework.entity.Account;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.bo.UserBO;
import com.hniesep.framework.entity.vo.UserInfoVO;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * @author 吉铭炼
 */
public interface AccountService {
    /**
     *根据用户名查询用户
     * @param username 用户名
     * @return boolean 返回一个user对象
     */
    UserBO selectByName(String username);
    /**
     *根据邮箱查询用户
     * @param email 用户名
     * @return boolean 返回一个user对象
     */
    UserBO selectByEmail(String email);
    /**
     * 选择所有用户
     * @return 返回所有user对象
     */
    ResponseResult<List<Account>> selectAll();
    /**
     * 设置图片验证码
     * @param rightCode 正确验证码
     * @param httpServletResponse http应答
     */
    void setVerificationImage(String rightCode, HttpServletResponse httpServletResponse);
    /**
     * 使用邮箱以及旧密码更改密码
     * @return 更改密码结果
     * @param userBO 用户业务对象
     */
    ResponseResult<Object> changePasswordByEmailAndOldPassword(UserBO userBO);
    /**
     * 用户名是否注册
     * @return 用户名是否注册
     * @param username 用户名
     */
    ResponseResult<Object> existUsername(String username);
    /**
     * 邮箱是否注册
     * @return 邮箱是否注册
     * @param email 邮箱
     */
    ResponseResult<Object> existEmail(String email);
    /**
     * 获取用户详细信息
     * @param accountId 用户id
     * @return 详用户详细信息细
     */
    ResponseResult<UserInfoVO> getUserInfo(Long accountId);
}
