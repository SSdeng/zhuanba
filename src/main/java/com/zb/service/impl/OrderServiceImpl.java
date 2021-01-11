package com.zb.service.impl;

import com.alibaba.druid.sql.dialect.mysql.visitor.transform.OrderByResolve;
import com.zb.entity.UserOrder;
import com.zb.exception.MyException;
import com.zb.repository.OrderRepository;
import com.zb.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.file.attribute.UserPrincipal;
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
    public UserOrder insertSelective(UserOrder order) {
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
        if(orderRepository.existsById(orderId)){
            orderRepository.deleteById(orderId);
            return true;
        }
        return false;
    }

    /**
     * 通过id查找订单
     *
     * @param orderId 订单id
     * @return 得到的订单
     */
    @Override
    public UserOrder getById(Long orderId) {
        UserOrder order = orderRepository.findById(orderId).orElse(null);
        if(order == null){
            throw new MyException("待查找订单不存在");
        }
        return order;
    }

    /**
     * 获取所有订单
     *
     * @return 订单列表
     */
    @Override
    public List<UserOrder> getAllOrder() {
        return orderRepository.findAll();
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
        return orderRepository.findAll(PageRequest.of(pageNo - 1, pageSize));
    }
}
