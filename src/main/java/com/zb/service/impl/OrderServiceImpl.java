package com.zb.service.impl;

import com.zb.entity.Item;
import com.zb.entity.User;
import com.zb.entity.UserOrder;
import com.zb.exception.MyException;
import com.zb.repository.UserOrderRepository;
import com.zb.repository.UserRepository;
import com.zb.service.ItemService;
import com.zb.service.OrderService;
import com.zb.service.UserService;
import com.zb.util.JsonTransfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    private UserService userService;
    @Resource
    private ItemService itemService;

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
        orderRepository.deleteById(orderId);
        return true;
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
    public UserOrder findById(Long orderId) {
        UserOrder order = getById(orderId);
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
        UserOrder order = findById(orderId);
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
        User user = userService.findById(userId);
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

    /**
     * 插入单个订单
     *
     * @param userId 用户id
     * @param itemId 商品id
     * @param count 商品数量
     * @return 结果订单
     */
    @Override
    public UserOrder addOrder(long userId, long itemId, int count) {
        User user = userService.findById(userId);
        Item item = itemService.findById(itemId);
        UserOrder order = new UserOrder();
        item.setCount(item.getCount()-count);
        order.setUser(user);
        order.setItem(item);
        order.setItemCount(count);
        order.setTotalPrice(item.getPrice().multiply(new BigDecimal(count)));
        return insertSelective(order);
    }

    /**
     * 获取id对应userOrder
     *
     * @param id userOrderId
     * @return userOrder对象
     */
    private UserOrder getById(Long id) {
        return id == null ? null : orderRepository.findById(id).orElse(null);
    }
}
