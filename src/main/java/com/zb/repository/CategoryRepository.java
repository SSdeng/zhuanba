package com.zb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zb.entity.Category;

/**
 * CategoryDAO层
 * 
 * @author dengzhijian
 * @version 1.0
 */
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {}