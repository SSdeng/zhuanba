package com.zb.service.impl;

import javax.annotation.Resource;

import com.zb.entity.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public Category insertSelective(Category newCategory) {
        if (getById(newCategory.getId()) != null) {
            throw new DataIntegrityViolationException("相同id的category已存在");
        }
        return categoryRepository.saveAndFlush(newCategory);
    }

    /**
     * 根据类别id删除类别
     *
     * @param category_id
     *            类别id
     * @return 删除结果
     */
    @Override
    public boolean deleteById(long category_id) {
        categoryRepository.deleteById(category_id);
        return true;
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
        return categoryRepository.save(category);
    }

    /**
     * 根据分类Id返回分类
     *
     * @param categoryId
     * @return 分类
     */
    @Override
    public Category findById(long categoryId){
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
    public List<Item> getSpecificCategoryItems(long categoryId){
        Category one = categoryRepository.getOne((long) categoryId);
        List<Item> items = one.getItems();
        return items;
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
    public Page<Category> findAllByPage(int pageNo, int pageSize) {
        return categoryRepository.findAll(PageRequest.of(pageNo - 1, pageSize));
    }

    /**
     * 获取id对应category
     *
     * @param id category_id
     * @return category对象 不存在返回null
     */
    private Category getById(Long id) {
        return id == null ? null : categoryRepository.findById(id).orElse(null);
    }
}
