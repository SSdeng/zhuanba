package com.zb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.zb.entity.Item;

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
    @Query(
        value = "SELECT * FROM sys_item WHERE id in (SELECT item_id FROM sys_item_category WHERE category_id = ?1) limit ?2 offset ?3",
        nativeQuery = true)
    List<Item> getSpecificCategoryItems(int categoryId, int pageSize, int startIndex);

    /**
     * 返回指定分类下的商品数量
     *
     * @param categoryId
     * @return
     */
    @Query(value = "SELECT count(item_id) FROM sys_item_category WHERE category_id = ?1", nativeQuery = true)
    int getSpecificCategoryItemsCount(int categoryId);

    /**
     * 返回指定分类下的商品数量 方式：JPA的命名规则方法
     *
     * @param id
     * @return
     */
    Page<Item> findItemsByCategories_id(Long id, Pageable pageable);

    /**
     * 根据商品状态分页查询
     *
     * @param status
     *            商品状态
     * @return
     */
    Page<Item> findAllByStatus(int status, Pageable pageable);

    /**
     * 根据状态返回所有商品
     * 
     * @param status
     * @return
     */
    List<Item> findAllByStatus(int status);

    /**
     * 按用户id返回商品
     *
     * @param userId 用户id
     * @return 商品list
     */
    @Query(value = "SELECT * FROM sys_item WHERE user_id = ?1", nativeQuery = true)
    List<Item> findByUserIdFromAll(long userId);

}