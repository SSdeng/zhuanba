package com.zb.service;

import com.zb.entity.Wants;
import com.zb.repository.WantsRepository;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 求购服务接口
 *
 * @author lijiacheng
 * @version 1.0
 */
public interface WantsService {
    /**
     * 新建求购项
     *
     * @param newWants 新求购
     * @return 新求购
     */
    Wants insertSelective(Wants newWants);

    /**
     * 通过id删除求购项
     *
     * @param wantsId 目标求购项id
     * @return 删除结果
     */
    boolean deleteById(Long wantsId);

    /**
     * 更新求购项
     *
     * @param wants 待更新求购
     * @return 更新后求购
     */
    Wants updateWants(Wants wants);

    /**
     * 通过id查找求购项
     *
     * @param wantsId 目标求购id
     * @return 查找结果
     */
    Wants findById(Long wantsId);

    /**
     * 获取所有求购
     *
     * @return 求购列表
     */
    List<Wants> getAllWants();

    /**
     * 分页获取所有求购
     *
     * @param pageNo 起始页码
     * @param pageSize 每页大小
     * @return 分页后求购列表
     */
    Page<Wants> getAllByPage(int pageNo, int pageSize);
}
