package com.zb.controller;

import com.zb.service.ItemCommentService;
import com.zb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CommentController
 * shenmanjie
 * 2021/1/10 23:16
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    ItemCommentService itemCommentService;


    /**
     * 构造器依赖注入
     *
     * @param itemCommentService 商品评论服务
     */
    @Autowired
    public CommentController(ItemCommentService itemCommentService) {
        this.itemCommentService = itemCommentService;
    }

    /**
     * @return 返回所有分类信息
     */
    @GetMapping("/item")
    public Result getItemComment(){
        return null;
    }
}
