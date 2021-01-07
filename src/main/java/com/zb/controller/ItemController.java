package com.zb.controller;

import com.zb.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品控制器
 *
 * @author lijiacheng
 * version 1.0
 */
@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/release")
    public String releaseItem(HttpRequest request){

        return null;
    }
}
