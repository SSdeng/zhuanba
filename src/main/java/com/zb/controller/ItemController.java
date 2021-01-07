package com.zb.controller;

import com.github.pagehelper.PageInfo;
import com.zb.pojo.Item;
import com.zb.service.ItemService;
import com.zb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商品控制器
 *
 * @author lijiacheng
 * @version 1.0
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

    /**
     * 查看指定id商品详情
     *
     * @param itemId 商品id
     * @return 查看结果
     */
    @GetMapping("/details")
    public Result itemDetails(@RequestParam("itemId") int itemId){
        Item item=itemService.findById(itemId);
        if(item == null){
            return Result.error("Find no item");
        }
        return Result.ok("Item Details",item);
    }

    /**
     * 分页查找所有商品
     *
     * @param pageNo 起始页码
     * @param pageSize 分页大小
     * @return 分页后所有商品
     */
    @GetMapping("/all")
    public Result itemAll(
            @RequestParam(value = "pageNo", defaultValue = "1")int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10")int pageSize){
        PageInfo<Item> items = itemService.findPage(pageNo,pageSize);
        return Result.ok("pageAll",items);
    }

    /**
     * 根据输入信息搜索商品
     *
     * @param searchInfo 搜索信息
     * @param pageNo 起始页码
     * @param pageSize 分页大小
     * @return 分页后搜索结果
     */
    @GetMapping("/search")
    public Result itemSearch(
            @RequestParam("searchInfo") String searchInfo,
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        PageInfo<Item> items = itemService.searchPage(searchInfo,pageNo,pageSize);
        return Result.ok("searchResultPage",items);
    }

}
