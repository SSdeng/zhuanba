package com.zb.service.impl;

import com.alibaba.druid.sql.dialect.mysql.visitor.transform.OrderByResolve;
import com.zb.entity.UserOrder;
import com.zb.repository.OrderRepository;
import com.zb.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单服务
 *
 * @author lijiacheng
 * @version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderRepository orderRepository;

    /**
     * 新建订单
     *
     * @param order 新订单
     * @return 新订单
     */
    @Override
    public UserOrder insertOrder(UserOrder order) {
        return orderRepository.saveAndFlush(order);
    }

    /**
     * 通过id删除订单
     *
     * @param orderId 订单id
     * @return 删除结果
     */
    @Override
    public boolean deleteById(Long orderId) {
        //todo
        orderRepository.deleteById(orderId);
    }

    /**
     * 更新订单
     *
     * @param order 要更新的订单
     * @return 更新后订单
     */
    @Override
    public UserOrder updateOrder(UserOrder order) {
        return null;
    }

    /**
     * 通过id查找订单
     *
     * @param orderId 订单id
     * @return 得到的订单
     */
    @Override
    public UserOrder getById(Long orderId) {
        return null;
    }

    /**
     * 获取所有订单
     *
     * @return 订单列表
     */
    @Override
    public List<UserOrder> getAllOrder() {
        return null;
    }

    /**
     * 分页获取所有订单
     *
     * @param pageNo   起始页码
     * @param pageSize 单页大小
     * @return 分页后订单
     */
    @Override
    public Page<UserOrder> getAllByPage(int pageNo, int pageSize) {
        return null;
    }
}
