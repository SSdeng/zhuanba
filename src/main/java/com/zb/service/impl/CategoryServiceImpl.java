package com.zb.service.impl;

import com.zb.mapper.CategoryMapper;
import com.zb.pojo.Category;
import com.zb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类别服务实现类
 *
 * @author YeFeng
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    /** 类别映射 */
    final private CategoryMapper categoryMapper;

    /**
     * 构造器依赖注入
     *
     * @param categoryMapper 类别映射
     */
    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    /**
     * 增加商品分类
     *
     * @param newCategory
     *            新增Category对象
     * @return 插入后Category对象
     */
    @Override
    public Category insertSelective(Category newCategory) {
        categoryMapper.insertSelective((newCategory));
        return newCategory;
    }


    /**
     * 根据类别id删除类别
     *
     * @param category_id 类别id
     * @return 删除结果
     */
    @Override
    public boolean deleteById(int category_id) {
        int cnt = categoryMapper.deleteByPrimaryKey(category_id);
        return cnt > 0;
    }

    /**
     * 更新类别信息
     *
     * @param category 待更新对象
     * @return 更新后对象
     */
    @Override
    public Category updateCategory(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
        return category;
    }

    /**
     * 根据类别名查找类别
     *
     * @param categoryName 类别名
     * @return 类别对象
     */
    @Override
    public Category findByName(String categoryName) {
        return categoryMapper.findByName(categoryName);
    }

}
