package com.zb.service;

import org.springframework.stereotype.Service;

import com.zb.pojo.Category;

@Service
public interface CategoryService {

    /**
     * 增加商品分类
     *
     * @param newCategory
     *            新增Category对象
     * @return 插入后Category对象
     */
    Category insertSelective(Category newCategory);

    /**
     * 给商品批量添加所属类别
     *
     * @param item_id
     *            商品id
     * @param category_ids
     *            类别id组
     */
    void insertList(int item_id, int[] category_ids);

    /**
     * 根据商品id删除商品-类别关联数据
     *
     * @param item_id
     *            商品id
     * @return 删除数据数
     */
    int removeByItem_id(int item_id);

}
