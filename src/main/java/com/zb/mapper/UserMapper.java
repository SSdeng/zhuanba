package com.zb.mapper;

import com.zb.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserDAO层
 * 
 * @author dengzhijian
 * @version 1.0
 */
public interface UserMapper {
    /**
     * 根据主键删除商品（真删除）
     * 
     * @param id
     *            主键
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新商品
     * 
     * @param record
     *            待插入商品
     */
    int insert(User record);

    /**
     * 插入新商品（不插入空字段）
     * 
     * @param record
     *            待插入商品
     */
    int insertSelective(User record);

    /**
     * 根据主键查询商品（包含分类列表和用户信息）
     * 
     * @param id
     *            主键
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
     * 根据主键更新商品（不更新空字段）
     * 
     * @param record
     *            新的商品
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据主键更新商品
     * 
     * @param record
     *            新的商品
     */
    int updateByPrimaryKey(User record);
}