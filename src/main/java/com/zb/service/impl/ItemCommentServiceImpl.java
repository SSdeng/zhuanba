package com.zb.service.impl;

import com.zb.entity.ItemComment;
import com.zb.repository.ItemCommentRepository;
import com.zb.service.ItemCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 商品评论服务实现类
 * shenmanjie
 * 2021/1/10 23:24
 */
@Service
public class ItemCommentServiceImpl implements ItemCommentService {
    @Resource
    private ItemCommentRepository itemCommentRepository;

    /**
     * 增加商品评论
     *
     * @param newItemComment 新增ItemComment对象
     * @return
     */
    @Override
    public ItemComment insertSelective(ItemComment newItemComment) {
        return itemCommentRepository.saveAndFlush(newItemComment);
    }
}
