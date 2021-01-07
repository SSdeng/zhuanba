package com.zb.mapper;

import com.zb.pojo.User;

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
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(User record);
}