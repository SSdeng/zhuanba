package com.zb.controller;

import com.zb.service.ItemService;
import com.zb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CategoryController
 * shenmanjie
 * 2021/1/8 11:23
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    ItemService itemService ;

    /**
     * 构造器依赖注入
     *
     * @param itemService 商品服务
     */
    @Autowired
    public CategoryController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/get")
    public Result getAllCategory(){
        return null;
    }
}
