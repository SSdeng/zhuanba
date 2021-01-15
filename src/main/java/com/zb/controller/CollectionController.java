package com.zb.controller;

import javax.annotation.Resource;

import com.zb.util.Base64Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zb.entity.Collection;
import com.zb.entity.User;
import com.zb.service.CollectionService;
import com.zb.util.Result;

/**
 * 收藏controller
 * 
 * @author whz 2021/1/11
 */

@Controller
@RequestMapping("/api/collection")
public class CollectionController {

    @Resource
    private CollectionService collectionService;

    /**
     * 向收藏中添加商品
     * 
     * @param userId
     *            用户id
     * @param itemId
     *            商品id
     * @return Result
     */
    @PostMapping("/add")
    @ResponseBody
    public Result addItem(@RequestParam("userId") String userId, @RequestParam("itemId") Long itemId) {
        collectionService.addItem(Base64Util.decode(userId), itemId);
        return Result.ok("收藏成功", null);

    }

    /**
     * 从收藏夹中移除商品
     * 
     * @param collectionId
     *            收藏id
     * @param itemId
     *            商品id
     * @return String
     */
    @PostMapping("/remove")
    @ResponseBody
    public Result removeItem(@RequestParam("collectionId") String collectionId, @RequestParam("itemId") Long itemId) {
        User user = collectionService.findById(Base64Util.decode(collectionId)).getUser();
        collectionService.removeItem(Base64Util.decode(collectionId), itemId);
        return Result.ok("收藏已移除", null);
    }

    /**
     * 详情页取消收藏商品
     *
     * @param userId
     *            用户Id
     * @param itemId
     *            商品Id
     * @return Result
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result deleteItem(@RequestParam("userId") String userId, @RequestParam("itemId") Long itemId) {
        Collection collection = collectionService.findByUser(Base64Util.decode(userId));
        collectionService.removeItem(collection.getId(), itemId);
        return Result.ok("已取消收藏", null);
    }

}
