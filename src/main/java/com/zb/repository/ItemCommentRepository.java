package com.zb.repository;

import com.zb.entity.ItemComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * ItemComment DAO
 * shenmanjie
 * 2021/1/10 23:19
 */
public interface ItemCommentRepository extends JpaRepository<ItemComment, Long>, JpaSpecificationExecutor<ItemComment> {


    /**
     * 通过ItemId返回评论该商品的所有评论
     *
     * @param item_id
     * @return
     */
    public List<ItemComment> getByItem_IdOrderByCreateTime(Long item_id);

}
