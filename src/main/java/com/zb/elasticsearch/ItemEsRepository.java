package com.zb.elasticsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.core.SearchPage;
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
    @Highlight(fields = {@HighlightField(name = "itemName"), @HighlightField(name = "description")},
        parameters = @HighlightParameters(preTags = "<strong><font style='color:red'>", postTags = "</strong>"))
    SearchPage<Item> findAllByItemNameOrDescription(String itemName, String description, Pageable pageable);

    /**
     * 分页返回指定分类的所有商品
     * 
     * @param id
     * @param pageable
     * @return
     */
    Page<Item> findAllByCategories_id(long id, Pageable pageable);
}
