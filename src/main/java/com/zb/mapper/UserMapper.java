package com.zb.mapper;

import com.zb.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户相关映射器
 */
public interface UserMapper {

    /**
     * 根据用户id删除用户
     *
     * @param id 用户id
     * @return 删除用户数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加用户
     *
     * @param record 用户
     * @return 添加用户数
     */
    int insert(User record);

    /**
     *安全添加用户
     *
     * @param record 用户
     * @return 添加用户数
     */
    int insertSelective(User record);

    /**
     * 根据用户id查找用户
     *
     * @param id 用户id
     * @return 查找到的用户
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 根据用户名查找User
     *
     * @param username 用户名
     * @return User对象
     */
    User findByUsername(@Param("username") String username);

    /**
     * 根据用户名和密码查找User
     *
     * @param username 用户名
     * @param password 密码
     * @return User对象
     */
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据学号查找User
     *
     * @param studentNumber 学号
     * @return User对象
     */
    User findByStudentNumber(@Param("studentNumber") String studentNumber);

    /**
     * 根据手机号查找User
     *
     * @param phoneNumber 手机号码
     * @return User对象
     */
    User findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    /**
     * 返回用户总数
     *
     * @return 用户总数
     */
    int count();

    /**
     *
     * @return
     */
    List<User> selectAll();

    /**
     * 安全更新用户
     *
     * @param record 用户
     * @return 更新用户数
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 更新用户
     *
     * @param record 用户
     * @return 更新用户数
     */
    int updateByPrimaryKey(User record);
}