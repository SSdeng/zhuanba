package com.zb.repository;

import com.zb.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * CategoryDAO层
 * 
 * @author dengzhijian
 * @version 1.0
 */
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    /**
     * 根据客户名称查询客户
     * 使用jpql的形式查询
     */
    @Query(value="from Category where name = ?")
    public Category findByName(String categoryName);
}