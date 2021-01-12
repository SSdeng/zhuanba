package com.zb.repository;

import com.zb.entity.User;
import com.zb.entity.WantsComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * WantsComment DAO层
 *
 * @author lijiacheng
 * @version 1.0
 */
public interface WantsCommentRepository extends JpaRepository<WantsComment, Long>, JpaSpecificationExecutor<WantsComment> {
}
