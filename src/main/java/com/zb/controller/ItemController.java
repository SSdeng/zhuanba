package com.zb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zb.enums.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.zb.entity.Item;
import com.zb.service.ItemService;
import com.zb.util.FileUtil;
import com.zb.util.Result;

/**
 * 商品控制器
 *
 * @author lijiacheng
 * @version 1.0
 */
@RestController
@RequestMapping("/api/item")
public class ItemController {

    final ItemService itemService;

    /**
     * 构造器依赖注入
     *
     * @param itemService
     *            商品服务
     */
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     @PostMapping("/release")
     public Result releaseItem(@RequestBody Map<String,String> map){
     Item item = new Item();
     item.setName(map.get("itemName"));
     item.setDescription(map.get("description"));
     item.setLevel(Integer.parseInt(map.get("level")));
     item.setPrice(Double.parseDouble(map.get("price")));
     item.setCount(Integer.parseInt(map.get("count")));
     int userId = Integer.parseInt(map.get("userId"));
     if(itemService.insert(item,userId)==null){
     return Result.error("Error Occured");
     }
     return Result.ok("Release Success");
     }*/

    /**
     * 发布商品处理
     *
     * @param item 新的商品
     * @return 发布结果
     */
    @PostMapping("/release")
    public Result releaseItem(@RequestBody Item item) {
        if (item == null) {
            return Result.error("Release Failed","");
        }
        itemService.insert(item);
        return Result.ok();
    }

    /**
     * 查看指定id商品详情
     *
     * @param itemId
     *            商品id
     * @return 查看结果
     */
    @GetMapping("/details")
    public Result itemDetails(@RequestParam("itemId") int itemId) {
        Item item = itemService.findById(itemId);
        if (item == null) {
            return Result.error("Item Not Found","");
        }
        return Result.ok(item);
    }

    /**
     * 分页查找所有商品
     *
     * @param pageNo
     *            起始页码
     * @param pageSize
     *            分页大小
     * @return 分页后所有商品
     */
    @GetMapping("/all")
    public Result itemAll(
            @RequestParam(value = "pageNo", defaultValue = "1")int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10")int pageSize){
        PageInfo<Item> items = itemService.findPage(pageNo,pageSize);
        if(items == null){
            return Result.error("No Item Existed","");
        }
        return Result.ok(getResult(items));
    }

    /**
     * 根据输入信息搜索商品
     *
     * @param searchInfo
     *            搜索信息
     * @param pageNo
     *            起始页码
     * @param pageSize
     *            分页大小
     * @return 分页后搜索结果
     */
    @GetMapping("/search")
    public Result itemSearch(
            @RequestParam("searchInfo") String searchInfo,
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        PageInfo<Item> items = itemService.searchPage(searchInfo,pageNo,pageSize);
        if(items == null){
            return Result.error("No Matched Item","");
        }
        return Result.ok(getResult(items));
    }

    /**
     * 接受文件图片
     *
     * @param itemId
     * @param image
     */
    @PostMapping("/upload")
    public void uploadPicture(@RequestParam("itemId") int itemId, @RequestParam("image") MultipartFile image) {

        // String itemPictureName = new String();

        try {
            // 使用图片上传工具类，接受文件后，返回文件的新名称
            String itemPictureName = FileUtil.uploadFile(image);

            Item item = itemService.findById(itemId);

            item.setImage(itemPictureName);

            itemService.updateItemInfo(item);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // return Result.ok("upload success");

    }

    /**
     * 提取PageInfo中的主要信息包装结果
     *
     * @param items 提取对象
     * @return 提取结果
     */
    private List<Map<String,String>> getResult(PageInfo<Item> items){
        List<Map<String,String>> result = new ArrayList<Map<String,String>>();
        for(Item item : items.getList()){
            Map<String,String> map = new HashMap<String, String>();
            map.put("itemId", Integer.toString(item.getId()));
            map.put("itemName", item.getName());
            map.put("price", item.getPrice().toString());
            map.put("image", item.getImage());
            result.add(map);
        }
        return result;
    }
}
