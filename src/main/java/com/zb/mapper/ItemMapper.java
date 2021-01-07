package com.zb.mapper;

import com.zb.pojo.Item;

/**
 * 商品相关映射器
 */
public interface ItemMapper {

    /**
     * 通过商品id删除商品
     *
     * @param id 商品id
     * @return 删除商品数
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