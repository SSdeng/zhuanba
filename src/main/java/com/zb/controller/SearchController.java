package com.zb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zb.elasticsearch.ItemEsRepository;
import com.zb.entity.Item;

/**
 * 检索控制器
 * 
 * @author dengzhijian
 * @version 1.0
 **/
@Controller
public class SearchController {
    @Resource
    private ItemEsRepository repository;

    /**
     * 根据关键词进行全文检索
     *
     * @param keyword
     * @return
     */
    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword,
        @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, Model model) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        SearchPage<Item> searchPage = repository.findAllByItemNameOrDescription(keyword, keyword, pageable);
        // 分页获得所有包含高亮命中和Item的SearchHits
        SearchHits<Item> searchHits = searchPage.getSearchHits();
        Long total = searchPage.getTotalElements();
        // 存储高亮后的商品
        ArrayList<Item> list = new ArrayList<>();
        // 迭代处理，将各项原字段处理为高亮字段
        for (SearchHit<Item> searchHit : searchHits) {
            // 获得原内容
            Item item = searchHit.getContent();
            // 获取命中字段
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            // 判断并填充
            if (highlightFields.containsKey("itemName")) {
                item.setItemName(highlightFields.get("itemName").toString());
            }
            if (highlightFields.containsKey("description")) {
                item.setItemName(highlightFields.get("description").toString());
            }
            // 加入商品列表
            list.add(item);
        }
        // 构建分页返回结果
        PageImpl<Item> items = new PageImpl<>(list, pageable, total);
        model.addAttribute("items", items);
        return "search";
    }
}
