package com.zb.mapper;

import com.zb.pojo.User;

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