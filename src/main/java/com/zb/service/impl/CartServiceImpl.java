package com.zb.service.impl;

import com.zb.entity.Cart;
import com.zb.entity.CartOrder;
import com.zb.entity.vo.CartOrderVO;
import com.zb.exception.MyException;
import com.zb.repository.CartRepository;
import com.zb.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车服务实现类
 *
 * @author YeFeng
 */
@Service
public class CartServiceImpl implements CartService {

    @Resource
    private CartRepository cartRepository;

    /**
     * 按id查找购物车
     *
     * @param id 购物车/用户id
     * @return 对应购物车
     */
    @Override
    public Cart findCartById(long id) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart == null) {
            throw new MyException("购物车不存在");
        }
        return cart;
    }

    /**
     * 添加订单到购物车
     *
     * @param itemId 商品id
     * @param count  数量
     * @return 购物车
     */
    @Override
    public Cart addOrder(long userId, long itemId, int count) {
        Cart cart = findCartById(userId);
        cart.getOrderList().add(new CartOrder(itemId, count));
        return cartRepository.save(cart);
    }

    /**
     * 移除购物车订单
     *
     * @param userId 用户id
     * @param itemId 商品id
     * @return 购物车
     */
    @Override
    public Cart removeOrder(long userId, long itemId) {
        Cart cart = findCartById(userId);
        List<CartOrder> list = cart.getOrderList();
        boolean flag = false;
        for (CartOrder order : list) {
            if (order.getItem().getId().equals(itemId)) {
                list.remove(order);
                flag = true;
                break;
            }
        }
        if (flag) {
            return cartRepository.save(cart);
        } else {
            throw new MyException("商品订单不存在");
        }
    }

    /**
     * 修改订单商品数量
     *
     * @param cartId 购物车id
     * @param itemId 商品id
     * @param count  数量
     * @return 购物车
     */
    @Override
    public Cart updateOrder(long cartId, long itemId, int count) {
        Cart cart = findCartById(cartId);
        List<CartOrder> list = cart.getOrderList();
        boolean flag = false;
        for (CartOrder order : list) {
            if (order.getItem().getId().equals(itemId)) {
                order.setItemCount(count);
                flag = true;
                break;
            }
        }
        if (flag) {
            return cartRepository.save(cart);
        } else {
            throw new MyException("商品订单不存在");
        }
    }

    /**
     * 获取购物车订单表
     *
     * @param cartId 购物车id
     */
    @Override
    public List<CartOrderVO> getCartOrderVoList(long cartId) {
        Cart cart = findCartById(cartId);
        List<CartOrderVO> list = new ArrayList<>();
        for (CartOrder cartOrder : cart.getOrderList()) {
            list.add(new CartOrderVO(cartOrder));
        }
        return list;
    }
}
