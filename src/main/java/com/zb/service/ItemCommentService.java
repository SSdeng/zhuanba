package com.zb.service;


import com.zb.entity.ItemComment;

import java.util.List;

/**
 * 商品评论接口类
 * shenmanjie
 * 2021/1/10 23:23
 */
public interface ItemCommentService {

    /**
     * 增加商品评论
     *
     * @param newItemComment
     *            新增ItemComment对象
     * @return 插入后ItemComment对象
     */
    ItemComment insertSelective(ItemComment newItemComment);

    /**
     * 返回指定商品Id的所有评论
     *
     * @param itemId
     * @return
     */
    List<ItemComment> getSpecificItemComments(Long itemId);

    /**
     * 根据id删除评论
     *
     * @param commentId 评论id
     * @return
     */
    boolean deleteById(Long commentId);


}
