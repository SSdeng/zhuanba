package com.zb.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
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

    @Resource
    private ItemService itemService;

    /**
     * 发布商品处理
     *
     * @param item
     *            新的商品
     * @return 发布结果
     */
    @PostMapping("/release")
    public Result releaseItem(@RequestBody Item item) {
        Item insert = itemService.insert(item);
        if (insert == null) {
            return Result.error("Error Occured");
        }
        return Result.ok("Release Success");
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
            return Result.error("Find no item");
        }
        return Result.ok("Item Details", item);
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
    public Result itemAll(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        PageInfo<Item> items = itemService.findPage(pageNo, pageSize);
        return Result.ok("pageAll", items);
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
    public Result itemSearch(@RequestParam("searchInfo") String searchInfo,
        @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<Item> items = itemService.searchPage(searchInfo, pageNo, pageSize);
        return Result.ok("searchResultPage", items);
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

}
