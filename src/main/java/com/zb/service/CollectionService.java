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
     * 分页展示所有收藏夹
     * @return
     */
    Page<Collection> getAllCollections(int pageNo,int pageSize);

    /**
     * 添加收藏夹
     * @param newCollection
     * @return
     */
    Collection addCollection(Collection newCollection);


    /**
     * 根据收藏夹id获取收藏夹
     * @param id
     * @return
     */
    Collection findById(Long id);

    /**
     * 根据收藏夹名称获取收藏夹
     * @param collectionName
     * @return
     */
    Collection findByName(String collectionName);

    /**
     * 按照id删除收藏夹
     * @param id
     * @return
     */
    boolean deleteById(Long id);

    /**
     * 更新收藏夹
     * @param JSONCollection
     * @param CollectionId
     * @return
     */
    Collection UpdateCollection(String JSONCollection,Long CollectionId);


}
