package com.zb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zb.entity.Category;
import com.zb.repository.CategoryRepository;
import com.zb.service.CategoryService;

/**
 * 类别服务实现类
 *
 * @author YeFeng
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryRepository categoryRepository;

    /**
     * 增加商品分类
     *
     * @param newCategory
     *            新增Category对象
     * @return 插入后Category对象
     */
    @Override
    public Category insertSelective(Category newCategory) {
        categoryRepository.insertSelective((newCategory));
        return newCategory;
    }

    /**
     * 根据类别id删除类别
     *
     * @param category_id
     *            类别id
     * @return 删除结果
     */
    @Override
    public boolean deleteById(int category_id) {
        int cnt = categoryRepository.deleteByPrimaryKey(category_id);
        return cnt > 0;
    }

    /**
     * 更新类别信息
     *
     * @param category
     *            待更新对象
     * @return 更新后对象
     */
    @Override
    public Category updateCategory(Category category) {
        categoryRepository.updateByPrimaryKeySelective(category);
        return category;
    }

    /**
     * 根据类别名查找类别
     *
     * @param categoryName
     *            类别名
     * @return 类别对象
     */
    @Override
    public Category findByName(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }

    /**
     * 分页查询分类
     *
     * @param pageNo
     *            起始页码
     * @param pageSize
     *            分页大小
     * @return 商品列表
     */
    @Override
    public PageInfo<Category> findPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Category> list = categoryRepository.selectAll();
        return new PageInfo<>(list);
    }
}
