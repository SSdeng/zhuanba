package com.zb.repository;

import com.zb.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * ItemDAO层
 * 
 * @author shenmanjie
 * @version 1.0
 */
public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {

    /**
     * 获取指定分类的商品分页
     *
     * @param categoryId
     * @param pageSize
     * @param startIndex
     * @return
     */
    @Query(value = "SELECT * FROM sys_item WHERE id in (SELECT item_id FROM sys_item_category WHERE category_id = ?1) limit ?2 offset ?3" ,nativeQuery = true)
    public List<Item> getSpecificCategoryItems(int categoryId, int pageSize, int startIndex);

    /**
     * 返回指定分类下的商品数量
     *
     * @param categoryId
     * @return
     */
    @Query(value = "SELECT count(item_id) FROM sys_item_category WHERE category_id = ?1", nativeQuery = true)
    public int getSpecificCategoryItemsCount(int categoryId);
}