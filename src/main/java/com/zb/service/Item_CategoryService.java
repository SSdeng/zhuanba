package com.zb.service;

import com.zb.pojo.Item_Category;

import java.util.List;

/**
 * 商品-类别服务接口
 *
 */
public interface Item_CategoryService {

    /**
     * 插入一个商品-类别实体
     *
     * @param entity 待保存实体
     * @return 更新信息后的entity
     */
    Item_Category insertSelective(Item_Category entity);

    /**
     * 对同一商品批量添加所属类别
     *
     * @param item_id 商品id
     * @param Category_ids 待添加类别id集合
     */
    void insertList(int item_id, int[] Category_ids);

    /**
     * 批量插入商品-类别实体
     *
     * @param entities 实体集合
     */
    void insertList(List<Item_Category> entities);

    /**
     * 通过商品id删除商品-类别关联数据
     *
     * @param item_id 文章id
     * @return 删除数据数
     */
    // removeByItemId(int item_id);
}
