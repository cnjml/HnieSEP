package com.hniesep.base.signinup.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.hniesep.base.entity.User;
import java.util.Date;
import java.util.List;

/**作者
 * @author HKRR
 */
@Mapper
@DS("user")
public interface UserMapper {
    /**
     *查询所有用户
     * @return 返回所有用户
     */
    List<User> selectAll();

    /**
     * 根据id查询用户
     * @param id id
     * @return 返回根据id选择的用户
     */
    User selectById(int id);

    /**
     *根据用户名查找用户
     * @param username 用户名
     * @return 返回一个用户
     *
     */
    User selectByName(String username);
    /**
     * 查询账号和密码
     * @param username 用户名
     * @param password 密码
     * @return 查询用户
     */

    User select(@Param("username") String username,@Param("password") String password);

    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @param regTime 注册时间
     */
    void insert(@Param("username")String username,@Param("password")String password,@Param("regTime") Date regTime);

}