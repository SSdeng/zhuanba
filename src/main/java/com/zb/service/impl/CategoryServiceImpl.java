package com.zb.service.impl;

import com.zb.entity.Category;
import com.zb.entity.Item;
import com.zb.exception.MyException;
import com.zb.repository.CategoryRepository;
import com.zb.repository.ItemRepository;
import com.zb.service.CategoryService;
import com.zb.util.JsonTransfer;
import com.zb.util.PaginationSupport;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private ItemRepository itemRepository;

    /**
     * 增加商品分类
     *
     * @param newCategory 新增Category对象
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
     * @param category_id 类别id
     * @return 删除结果
     */
    @Override
    public boolean deleteById(long category_id) {
        categoryRepository.deleteById(category_id);
        return true;
    }

    /**
     * 更新分类信息
     * 
     * @param json json字符串
     * @param categoryId 分类id
     * @return 分类对象
     */
    @Override
    public Category updateCategory(String json, long categoryId) {
        Category category = findById(categoryId);
        category = JsonTransfer.updateSelective(json, category);
        return categoryRepository.saveAndFlush(category);
    }

    /**
     * 根据分类Id返回分类
     *
     * @param categoryId
     * @return 分类
     */
    @Override
    public Category findById(long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if(category == null){
            throw new MyException("待查找分类不存在");
        }
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
    public List<Item> getSpecificCategoryItems(long categoryId) {
        Category one = categoryRepository.getOne((long) categoryId);
        List<Item> items = one.getItems();
        return items;
    }

    /**
     * 分页查询分类
     *
     * @param pageNo   起始页码
     * @param pageSize 分页大小
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
    public Category getById(Long id) {
        return id == null ? null : categoryRepository.findById(id).orElse(null);
    }

    /**
     * 分页返回指定分类下的商品
     * 实现方式：sql语句查询
     *
     * @param categoryId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PaginationSupport<Item> getSpecificCategoryItems(int categoryId, int pageNo, int pageSize) {
        int totalCount = itemRepository.getSpecificCategoryItemsCount(categoryId);
        int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
        if (totalCount < 1) {
            return new PaginationSupport<Item>(new ArrayList<Item>(0), 0);
        }
        List<Item> items = itemRepository.getSpecificCategoryItems(categoryId, pageSize, startIndex);
        PaginationSupport<Item> ps = new PaginationSupport<Item>(items, totalCount, pageSize, startIndex);

        return ps;
    }

    /**
     * 分页返回指定分类下的商品
     * 实现方式：JPA命名规则
     *
     * @param categoryId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Item> getSpecificCategoryItemsByNamingParameters(Long categoryId, int pageNo, int pageSize){

        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        Page<Item> items = itemRepository.findItemsByCategories_id(categoryId, pageable);

        return items;
    }

}
