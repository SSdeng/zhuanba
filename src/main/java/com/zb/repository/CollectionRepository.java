package com.zb.repository;

import com.zb.entity.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Collection Dao
 * @author whz
 * 2021/1/10
 */
public interface CollectionRepository extends JpaRepository<Collection,Long> , JpaSpecificationExecutor<Collection> {
    /**
     * 根据收藏夹名称查找收藏夹
     * 使用jpql的形式查询
     */
    Collection findByName(String collectionName);




}
