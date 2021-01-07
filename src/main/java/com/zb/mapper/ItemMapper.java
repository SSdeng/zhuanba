package com.zb.mapper;

import java.util.List;

import com.zb.pojo.Item;

/**
 * ItemDAO层
 * 
 * @author dengzhijian
 * @version 1.0
 */
public interface ItemMapper {
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
    int insert(Item record);

    /**
     * 插入新商品（不插入空字段）
     * 
     * @param record
     *            待插入商品
     */
    int insertSelective(Item record);

    /**
     * 根据主键查询商品（包含分类列表和用户信息）
     * 
     * @param id
     *            主键
     */
    Item selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新商品（不更新空字段）
     * 
     * @param record
     *            新的商品
     */
    int updateByPrimaryKeySelective(Item record);

    /**
     * 根据主键更新商品
     * 
     * @param record
     *            新的商品
     */
    int updateByPrimaryKey(Item record);

    /**
     * 根据用户id查找商品
     */
    List<Item> selectByUserId(Integer id);

    /**
     * 根据商品id查找所有商品
     */
    List<Item> selectByCategoryId(Integer id);
}