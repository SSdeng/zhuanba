package com.zb.service;

import com.zb.entity.Collection;
import com.zb.entity.UserOrder;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 订单服务接口
 *
 * @author lijiacheng
 * @version 1.0
 */
public interface OrderService {
    /**
     * 新建订单
     *
     * @param order 新订单
     * @return 新订单
     */
    UserOrder insertSelective(UserOrder order);

    /**
     * 通过id删除订单
     *
     * @param orderId 订单id
     * @return 删除结果
     */
    boolean deleteById(Long orderId);

    /**
     * 通过id查找订单
     *
     * @param orderId 订单id
     * @return 得到的订单
     */
    UserOrder getById(Long orderId);

    /**
     * 获取所有订单
     *
     * @return 订单列表
     */
    List<UserOrder> getAllOrder();

    /**
     * 分页获取所有订单
     *
     * @param pageNo 起始页码
     * @param pageSize 单页大小
     * @return 分页后订单
     */
    Page<UserOrder> getAllByPage(int pageNo, int pageSize);
}
