package com.zb.repository;

import com.zb.entity.WantsComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * WantsComment DAO层
 *
 * @author lijiacheng
 * @version 1.0
 */
public interface WantsCommentRepository extends JpaRepository<WantsComment, Long>, JpaSpecificationExecutor<WantsComment> {

    /**
     * 分页查找用户的所有求购评论
     *
     * @param userId 用户id
     * @param pageable 分页请求
     * @return 分页评论表
     */
    Page<WantsComment> findAllByUser_Id(Long userId, Pageable pageable);

    /**
     * 分页查找求购下的所有评论
     *
     * @param wantsId 求购id
     * @param pageable 分页请求
     * @return 分页评论表
     */
    Page<WantsComment> findAllByWants_Id(Long wantsId, Pageable pageable);
}
