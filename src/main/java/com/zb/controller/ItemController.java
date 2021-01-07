package com.zb.controller;

import com.zb.pojo.Item;
import com.zb.service.ItemService;
import com.zb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 商品发布处理
     *
     * @param item 发布商品
     * @return 发布结果
     */
    @PostMapping("/release")
    public Result releaseItem(@RequestBody Item item){
        if(itemService.insert(item)==null){
            return Result.error("Error Occured");
        }
        return Result.ok("Release Success");
    }

    @GetMapping("/details")
    public Item itemDetails(@RequestParam int itemId){
        Item item=itemService.selectById(itemId);

    }
}
