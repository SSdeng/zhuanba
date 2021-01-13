package com.zb.controller;

import com.zb.entity.ItemComment;
import com.zb.entity.WantsComment;
import com.zb.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Map;

/**
 * CommentController
 * shenmanjie
 * 2021/1/10 23:16
 */
@Controller
@RequestMapping("/api/comment")
public class CommentController {

    @Resource
    ItemCommentService itemCommentService;

    @Resource
    UserService userService;

    @Resource
    ItemService itemService;

    @Resource
    private WantsService wantsService;

    @Resource
    private WantsCommentService wCommentService;

    /**
     * @return 增加商品评论
     */
    @PostMapping("/item")
    public String addItemComment(@RequestParam("itemId") long itemId, Map<String, Object> map) {

        int userId = (int) map.get("userId");
        String content = (String) map.get("content");

        ItemComment itemComment = new ItemComment();
        itemComment.setUser(userService.findById(userId));
        itemComment.setItem(itemService.findById(itemId));
        itemComment.setContent(content);

        itemCommentService.insertSelective(itemComment);

        //response.sendRedirect("/api/item/details");
        return "/api/item/details";
    }

    /**
     * 新增求购评论
     *
     * @param wantsId 求购id
     * @param map 参数集合
     * @return 求购详情页
     */
    @PostMapping("/wants")
    public String addWantsComment(@RequestParam("wantsId") long wantsId, @RequestBody Map<String, Object> map){
        long userId = (long)map.get("userId");
        WantsComment newComment = new WantsComment();
        newComment.setContent((String)map.get("content"));
        newComment.setUser(userService.findById(userId));
        newComment.setWants(wantsService.findById(wantsId));
        wCommentService.insertSelective(newComment);
        return "/api/wants/details";
    }
}
