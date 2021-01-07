package com.zb.mapper;

import generate.Item;

/**
 * @Entity generate.Item
 */
public interface ItemMapper {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @mbg.generated
     */
    int insert(Item record);

    /**
     * @mbg.generated
     */
    int insertSelective(Item record);

    /**
     * @mbg.generated
     */
    Item selectByPrimaryKey(Integer id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Item record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(Item record);

    /**
     *
     */
    Item selectByUserId(Integer userId);
}