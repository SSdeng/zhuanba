package com.zb.mapper;

import com.zb.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity generate.User
 */
public interface UserMapper {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @mbg.generated
     */
    int insert(User record);

    /**
     * @mbg.generated
     */
    int insertSelective(User record);

    /**
     * @mbg.generated
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
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(User record);
}