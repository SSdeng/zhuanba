package com.zb.service.impl;

import com.zb.entity.User;
import com.zb.entity.UserOrder;
import com.zb.exception.MyException;
import com.zb.repository.UserOrderRepository;
import com.zb.repository.UserRepository;
import com.zb.service.OrderService;
import com.zb.util.JsonTransfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private UserOrderRepository orderRepository;
    @Resource
    private UserRepository userRepository;

    /**
     * 新建订单
     *
     * @param newOrder 新订单
     * @return 新订单
     */
    @Override
    public UserOrder insertSelective(UserOrder newOrder) {
        return orderRepository.saveAndFlush(newOrder);
    }

    /**
     * 通过id删除订单
     *
     * @param orderId 订单id
     * @return 删除结果
     */
    @Override
    public boolean deleteById(Long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
            return true;
        }
        return false;
    }

    /**
     * 更新订单
     *
     * @param json  json字符串
     * @param order 待更新订单
     * @return 更新后订单
     */
    public UserOrder updateOrder(String json, UserOrder order) {
        order = JsonTransfer.updateSelective(json, order);
        return orderRepository.save(order);
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
        if (order == null) {
            throw new MyException("待查找订单不存在");
        }
        return order;
    }

    /**
     * 更新订单状态
     *
     * @param orderId   订单id
     * @param newStatus 新状态
     * @return 更新后订单
     */
    @Override
    public UserOrder setOrderStatus(Long orderId, Integer newStatus) {
        UserOrder order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new MyException("目标订单不存在");
        }
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    /**
     * 获取指定用户所有订单
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<UserOrder> getPageByUser(Long userId, int pageNo, int pageSize) {
        return orderRepository.findAllByUser_Id(userId, PageRequest.of(pageNo - 1, pageSize));
    }

    /**
     * 通过状态分页获取订单
     *
     * @param userId   用户id
     * @param status   订单状态
     * @param pageNo   起始页码
     * @param pageSize 分页大小
     * @return 分页结果
     */
    @Override
    public Page<UserOrder> getPageByStatus(Long userId, Integer status, int pageNo, int pageSize) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new MyException("目标用户不存在");
        }
        return orderRepository.findAllByUser_IdAndStatus(user.getId(), status, PageRequest.of(pageNo - 1, pageSize));
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
