package com.zb.repository;

import com.zb.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * CategoryDAO层
 * 
 * @author dengzhijian
 * @version 1.0
 */
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    /**
     * 根据分类名称查询分类
     * 使用jpql的形式查询
     */
    @Query(value="from Category where name = ?1")
    public Category findByName(String categoryName);

    /**
     * 根据一组id查询分类
     *
     * @param cIds id数组
     * @return 分类集合
     */
    public List<Category> findByIdIn(Long[] cIds);
}