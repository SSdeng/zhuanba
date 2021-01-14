package com.zb.controller;


import com.zb.entity.Collection;
import com.zb.entity.User;
import com.zb.service.CollectionService;
import com.zb.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 收藏controller
 * @author whz
 * 2021/1/11
 */

@Controller
@RequestMapping("/api/collection")
public class CollectionController {

    @Resource
    private CollectionService collectionService;

    /**
     * 向收藏中添加商品
     * @param userId 用户id
     * @param itemId 商品id
     * @return Result
     */
    @PostMapping("/add")
    @ResponseBody
    public Result addItem(@RequestParam("userId")Long  userId, @RequestParam("itemId") Long itemId){
        collectionService.addItem(userId,itemId);
        return Result.ok();

    }

    /**
     * 从收藏中移除商品
     * @param collectionId 收藏id
     * @param itemId 商品id
     * @return String
     */
    @PostMapping("/remove")
    public String removeItem(@RequestParam("userId")Long userId
            , @RequestParam("itemId") Long itemId){
        Collection collection = collectionService.findByUser(userId);
        collectionService.removeItem(collection.getId(), itemId);
        return "redirect:/api/user/info?userId" + userId;
    }

}
