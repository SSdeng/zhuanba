package com.zb.mapper;

import java.util.List;

import com.zb.pojo.Item;

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
     * 分页返回商品列表
     *
     * @return 商品列表
     */
    List<Item> selectALl();

    /**
     * 根据用户id查找商品
     */
    List<Item> selectByUserId(Integer id);

    /**
     * 根据分类id查找所有商品
     */
    List<Item> selectByCategoryId(Integer id);
}