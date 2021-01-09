package com.zb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zb.entity.Item;

/**
 * ItemDAO层
 * 
 * @author dengzhijian
 * @version 1.0
 */
public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {}