package com.zb.service;

import com.zb.entity.WantsComment;

/**
 * 求购评论服务接口
 *
 * @author lijiacheng
 * @version 1.0
 */
public interface WantsCommentService {

    /**
     * 新增求购评论
     *
     * @param newComment 新评论
     * @return 插入后求购评论
     */
    WantsComment insertSelective (WantsComment newComment);
}
