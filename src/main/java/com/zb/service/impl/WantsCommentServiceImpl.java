package com.zb.service.impl;

import com.zb.entity.WantsComment;
import com.zb.repository.WantsCommentRepository;
import com.zb.service.WantsCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lijiacheng
 * @version 1.0
 */
@Service
public class WantsCommentServiceImpl implements WantsCommentService {

    @Resource
    private WantsCommentRepository wantsCommentRepository;
    /**
     * 新增求购评论
     *
     * @param newComment 新评论
     * @return 插入后求购评论
     */
    @Override
    public WantsComment insertSelective(WantsComment newComment) {
        return wantsCommentRepository.saveAndFlush(newComment);
    }
}
