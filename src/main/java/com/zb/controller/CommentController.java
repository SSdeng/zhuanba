package com.zb.controller;

import java.util.Map;

import javax.annotation.Resource;

import com.zb.util.Base64Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zb.entity.ItemComment;
import com.zb.entity.WantsComment;
import com.zb.service.*;

/**
 * 评论Controller
 * 
 * @author shenmanjie 2021/1/10 23:16
 */
@Controller
@RequestMapping("/api/comment")
public class CommentController {


    @Resource
    UserService userService;
    @Resource
    ItemService itemService;
    @Resource
    private WantsService wantsService;
    @Resource
    ItemCommentService itemCommentService;
    @Resource
    private WantsCommentService wCommentService;

    /**
     * @return 增加商品评论
     */
    @PostMapping("/item")
    public String addItemComment(@RequestParam("itemId") long itemId, @RequestBody Map<String, String> map) {

        String userId = (map.get("userId"));
        String content = map.get("content");

        ItemComment itemComment = new ItemComment();
        itemComment.setUser(userService.findById(Base64Util.decode(userId)));
        itemComment.setItem(itemService.findById(itemId));
        itemComment.setContent(content);

        itemCommentService.insertSelective(itemComment);
        return "redirect:/api/item/details";
    }

    /**
     * 新增求购评论
     *
     * @param wantsId
     *            求购id
     * @param map
     *            参数集合
     * @return 求购详情页
     */
    @PostMapping("/wants")
    public String addWantsComment(@RequestParam("wantsId") long wantsId, @RequestBody Map<String, String> map) {

        String userId = (map.get("userId"));

        WantsComment newComment = new WantsComment();
        newComment.setContent(map.get("content"));
        newComment.setUser(userService.findById(Base64Util.decode(userId)));
        newComment.setWants(wantsService.findById(wantsId));

        wCommentService.insertSelective(newComment);
        return "redirect:/api/wants/details";
    }
}
