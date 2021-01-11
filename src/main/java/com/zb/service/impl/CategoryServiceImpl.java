package com.zb.service.impl;

import com.zb.entity.Category;
import com.zb.entity.Item;
import com.zb.repository.CategoryRepository;
import com.zb.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类服务实现类
 *
 * @author shenmanjie
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
    public Category addCategory(Category newCategory) {

        Category save = categoryRepository.save(newCategory);

        return save;
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
        categoryRepository.deleteById((long) category_id);
        return categoryRepository.existsById((long) category_id);
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
        Category newCategory = categoryRepository.save(category);
        return newCategory;
    }

    /**
     * 根据分类Id返回分类
     *
     * @param categoryId
     * @return 分类
     */
    @Override
    public Category findById(int categoryId){
        return categoryRepository.getOne((long) categoryId);
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
     * 返回所有类别
     *
     * @return 类别List
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    /**
     * 返回分类中的商品
     *
     * @param categoryId
     * @return
     */
    @Override
    public List<Item> getSpecificCategoryItems(int categoryId){
        Category one = categoryRepository.getOne((long) categoryId);
        List<Item> items = one.getItems();
        return items;
    }
//
//    /**
//     * 分页查询分类
//     *
//     * @param pageNo
//     *            起始页码
//     * @param pageSize
//     *            分页大小
//     * @return 商品列表
//     */
//    @Override
//    public PageInfo<Category> findPage(int pageNo, int pageSize) {
//        PageHelper.startPage(pageNo, pageSize);
//        List<Category> list = categoryRepository.selectAll();
//        return new PageInfo<>(list);
//    }


}
