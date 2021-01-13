package com.zb.service;

import com.zb.entity.WantsComment;
import org.springframework.data.domain.Page;

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

    /**
     * 根据id删除求购评论
     *
     * @param commentId 评论id
     * @return 删除结果
     */
    boolean deleteById(Long commentId);

    /**
     * 根据id查找求购评论
     *
     * @param commentId 评论id
     * @return 找到的评论
     */
    WantsComment findById(Long commentId);

    /**
     * 分页获取用户的所有求购评论
     *
     * @param userId 用户id
     * @param pageNo 起始页码
     * @param pageSize 分页大小
     * @return 分页后用户求购评论
     */
    Page<WantsComment> getAllByUser(Long userId, int pageNo, int pageSize);

    /**
     * 分页获取求购的所有求购评论
     *
     * @param wantsId 求购评论
     * @param pageNo 起始页码
     * @param pageSize 分页大小
     * @return 分页后求购的评论
     */
    Page<WantsComment> getAllByWants(Long wantsId, int pageNo, int pageSize);
}
