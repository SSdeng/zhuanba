package com.zb.service;

import com.zb.entity.Category;
import com.zb.entity.Item;

import java.util.List;

/**
 * 分类服务接口
 *
 * @author shenmanjie
 */
public interface CategoryService {

    /**
     * 增加商品分类
     *
     * @param newCategory
     *            新增Category对象
     * @return 插入后Category对象
     */
    Category addCategory(Category newCategory);

    /**
     * 根据类别id删除分类
     *
     * @param category_id
     *            类别id
     * @return 删除结果
     */
    boolean deleteById(int category_id);

    /**
     * 更新分类信息
     *
     * @param category
     *            待更新对象
     * @return 更新后对象
     */
    Category updateCategory(Category category);

    /**
     * 根据分类Id查找分类
     *
     * @param categoryId
     * @return
     */
    Category findById(int categoryId);

    /**
     * 根据类别名查找分类
     *
     * @param categoryName
     *            类别名
     * @return 类别对象
     */
    Category findByName(String categoryName);

    /**
     * 返回所有分类
     *
     * @return 类别List
     */
    List<Category> getAllCategories();


    /**
     * 返回分类下的商品信息
     *
     * @param categoryId
     * @return
     */
    List<Item> getSpecificCategoryItems(int categoryId);
//
//    /**
//     * 分页查询
//     *
//     * @param pageNo
//     *            起始页码
//     * @param pageSize
//     *            分页大小
//     * @return 商品列表
//     */
//    PageInfo<Category> findPage(int pageNo, int pageSize);
}
