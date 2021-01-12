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
     * @param newOrder 新订单
     * @return 新订单
     */
    UserOrder insertSelective(UserOrder newOrder);

    /**
     * 通过id删除订单
     *
     * @param orderId 订单id
     * @return 删除结果
     */
    boolean deleteById(Long orderId);

    /**
     * 更新订单
     *
     * @param order 待更新订单
     * @return 更新后订单
     */
    UserOrder updateOrder(UserOrder order);

    /**
     * 通过id查找订单
     *
     * @param orderId 订单id
     * @return 得到的订单
     */
    UserOrder getById(Long orderId);

    /**
     * 更新订单状态
     *
     * @param orderId 订单id
     * @param newStatus 新状态
     * @return 更新后订单
     */
    UserOrder setOrderStatus(Long orderId, Integer newStatus);

    /**
     * 通过状态分页获取订单
     *
     * @param userId 用户id
     * @param status 订单状态
     * @param pageNo 起始页码
     * @param pageSize 分页大小
     * @return 分页结果
     */
    Page<UserOrder> getPageByStatus(Long userId, Integer status, int pageNo, int pageSize);

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
