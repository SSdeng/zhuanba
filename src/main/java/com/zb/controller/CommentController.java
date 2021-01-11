package com.zb.controller;

import com.zb.entity.ItemComment;
import com.zb.service.ItemCommentService;
import com.zb.service.ItemService;
import com.zb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * @return 增加商品评论
     */
    @PostMapping("/item")
    public String addItemComment(@RequestParam("itemId") int itemId, Map<String, Object> map) {

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
}
