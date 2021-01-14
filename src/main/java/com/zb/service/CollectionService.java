package com.zb.service;

import com.zb.entity.Collection;
import com.zb.entity.Item;

/**
 * 收藏接口服务
 * @author whz
 * 2021/1/11
 */
public interface CollectionService {

    /**
     * 根据收藏夹id获取收藏夹
     * @param id 收藏id
     * @return Collection
     */
    Collection findById(Long id);

    /**
     * 根据用户id获取收藏夹
     *
     * @param userId
     * @return
     */
    Collection findByUser(Long userId);

    /**
     * 添加商品到收藏夹
     * @param userId 用户id
     * @param itemId 商品id
     * @return Collection
     */
    Collection addItem(Long userId, Long itemId);

    /**
     * 从收藏夹移除商品
     * @param collectionId 收藏id
     * @param itemId 商品id
     * @return Collection
     */
    Collection removeItem(Long collectionId , Long itemId);

    /**
     * 检查收藏夹中是否已有商品
     *
     * @param itemId 商品id
     * @return true表有 false表无
     */
    boolean hasItem(long id, long itemId);
}
