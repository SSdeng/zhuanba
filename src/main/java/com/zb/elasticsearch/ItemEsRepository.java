package com.zb.elasticsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.zb.entity.Item;

/**
 * 处理商品的全文检索
 * 
 * @author dengzhijian
 * @version 1.0
 **/
public interface ItemEsRepository extends ElasticsearchRepository<Item, Long> {

    /**
     * 从索引库中分页返回指定分类的商品
     * 
     * @param id
     *            分类id
     * @param pageable
     *            分页参数
     * @return 商品列表
     */
    Page<Item> findAllByCategories_id(long id, Pageable pageable);

    /**
     * 从索引库中分页返回检索到的商品，并且高亮
     * 
     * @param itemName
     *            商品名称
     * @param description
     *            商品描述
     * @param pageable
     *            分页参数
     * @return 包含高亮的商品列表
     */
    Page<SearchHit<Item>> findAllByItemNameOrDescription(String itemName, String description, Pageable pageable);
}
