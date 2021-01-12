package com.zb.service;

import com.zb.entity.Collection;
import org.springframework.data.domain.Page;

/**
 * 收藏接口服务
 * @author whz
 * 2021/1/11
 */
public interface CollectionService {



    /**
     * 根据收藏夹id获取收藏夹
     * @param id
     * @return
     */
    Collection findById(Long id);

    /**
     * 添加商品到收藏夹
     * @param userId
     * @param itemId
     * @return
     */
    Collection addItem(Long userId, Long itemId);

    /**
     * 从收藏夹移除商品
     * @param collectionId
     * @param itemId
     */
    Collection removeItem(Long collectionId , Long itemId);




}
