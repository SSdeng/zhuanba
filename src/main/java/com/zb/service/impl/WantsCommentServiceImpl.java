package com.zb.service.impl;

import com.zb.entity.WantsComment;
import com.zb.exception.MyException;
import com.zb.repository.WantsCommentRepository;
import com.zb.service.WantsCommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 求购评论服务
 *
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

    /**
     * 根据id删除求购评论
     *
     * @param commentId 评论id
     * @return 删除结果
     */
    @Override
    public boolean deleteById(Long commentId) {
        wantsCommentRepository.deleteById(commentId);
        return true;
    }

    /**
     * 根据id查找求购评论
     *
     * @param commentId 评论id
     * @return 找到的评论
     */
    @Override
    public WantsComment findById(Long commentId) {
        WantsComment comment = wantsCommentRepository.findById(commentId).orElse(null);
        if(comment == null){
            throw new MyException("待查找求购评论不存在");
        }
        return comment;
    }

    /**
     * 分页获取用户的所有求购评论
     *
     * @param userId   用户id
     * @param pageNo   起始页码
     * @param pageSize 分页大小
     * @return 分页后用户求购评论
     */
    @Override
    public Page<WantsComment> getAllByUser(Long userId, int pageNo, int pageSize) {
        return wantsCommentRepository.findAllByUser_Id(userId, PageRequest.of(pageNo - 1, pageSize));
    }

    /**
     * 分页获取求购的所有求购评论
     *
     * @param wantsId  求购评论
     * @param pageNo   起始页码
     * @param pageSize 分页大小
     * @return 分页后求购的评论
     */
    @Override
    public Page<WantsComment> getAllByWants(Long wantsId, int pageNo, int pageSize) {
        return wantsCommentRepository.findAllByWants_Id(wantsId, PageRequest.of(pageNo - 1, pageSize));
    }

}
