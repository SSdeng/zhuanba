package com.zb.service;

import com.github.pagehelper.PageInfo;
import com.zb.pojo.Category;


/**
 * 类别服务接口
 *
 * @author YeFeng
 */
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
     * 根据类别id删除类别
     *
     * @param category_id 类别id
     * @return 删除结果
     */
    boolean deleteById(int category_id);

    /**
     * 更新类别信息
     *
     * @param category 待更新对象
     * @return 更新后对象
     */
    Category updateCategory(Category category);

    /**
     * 根据类别名查找类别
     *
     * @param categoryName 类别名
     * @return 类别对象
     */
    Category findByName(String categoryName);

    /**
     * 分页查询
     *
     * @param pageNo
     *            起始页码
     * @param pageSize
     *            分页大小
     * @return 商品列表
     */
    PageInfo<Category> findPage(int pageNo, int pageSize);
}
