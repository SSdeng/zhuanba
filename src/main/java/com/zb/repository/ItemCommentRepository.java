package com.zb.repository;

import com.zb.entity.ItemComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ItemComment DAO
 * shenmanjie
 * 2021/1/10 23:19
 */
public interface ItemCommentRepository extends JpaRepository<ItemComment, Long>, JpaSpecificationExecutor<ItemComment> {
}
