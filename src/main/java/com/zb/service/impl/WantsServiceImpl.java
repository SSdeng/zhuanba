package com.zb.service.impl;

import com.zb.entity.UserOrder;
import com.zb.entity.Wants;
import com.zb.exception.MyException;
import com.zb.repository.WantsRepository;
import com.zb.service.WantsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 求购服务
 *
 * @author lijiacheng
 * @version 1.0
 */
@Service
public class WantsServiceImpl implements WantsService {

    @Resource
    private WantsRepository wantsRepository;

    /**
     * 新建求购项
     *
     * @param newWants 新求购
     * @return 新求购
     */
    @Override
    public Wants insertSelective(Wants newWants) {
        return wantsRepository.saveAndFlush(newWants);
    }

    /**
     * 通过id删除求购项
     *
     * @param wantsId 目标求购项id
     * @return 删除结果
     */
    @Override
    public boolean deleteById(Long wantsId) {
        if(wantsRepository.existsById(wantsId)){
            wantsRepository.deleteById(wantsId);
            return true;
        }
        return false;
    }

    /**
     * 更新求购项
     *
     * @param wants 待更新求购
     * @return 更新后求购
     */
    @Override
    public Wants updateWants(Wants wants) {
        return wantsRepository.saveAndFlush(wants);
    }

    /**
     * 通过id查找求购项
     *
     * @param wantsId 目标求购id
     * @return 查找结果
     */
    @Override
    public Wants getById(Long wantsId) {
        Wants wants = wantsRepository.findById(wantsId).orElse(null);
        if(wants == null){
            throw new MyException("待查找求购项不存在");
        }
        return wants;
    }

    /**
     * 获取所有求购
     *
     * @return 求购列表
     */
    @Override
    public List<Wants> getAllWants() {
        return wantsRepository.findAll();
    }

    /**
     * 分页获取所有求购
     *
     * @param pageNo   起始页码
     * @param pageSize 每页大小
     * @return 分页后求购列表
     */
    @Override
    public Page<Wants> getAllByPage(int pageNo, int pageSize) {
        return wantsRepository.findAll(PageRequest.of(pageNo, pageSize));
    }
}
