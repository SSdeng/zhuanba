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
    public Cart findCartById(Long id) {
        Cart cart = getById(id);
        if (cart == null) {
            throw new MyException("购物车不存在");
        }
        return cart;
    }

    /**
     * 检查购物车中是否已有商品
     *
     * @param itemId 商品id
     * @return true表有 false表无
     */
    @Override
    public boolean hasItem(long id, long itemId) {
        return findCartOrderByItemId(findCartById(id).getOrderList(), itemId) != null;
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
        if (hasItem(cart, itemId)) {
            throw new MyException("商品已在购物车中！");
        }
        cart.getOrderList().add(new CartOrder(cart, itemId, count));
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
        CartOrder cartOrder = findCartOrderByItemId(list, itemId);
        if (cartOrder != null) {
            list.remove(cartOrder);
        } else {
            throw new MyException("商品订单不存在");
        }
        return cartRepository.save(cart);
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
        CartOrder cartOrder = findCartOrderByItemId(list, itemId);
        if (cartOrder != null) {
            cartOrder.setItemCount(count);
        } else {
            throw new MyException("商品订单不存在");
        }
        return cartRepository.save(cart);
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

    /**
     * 按商品id查找购物车中订单
     *
     * @param list   购物车
     * @param itemId 商品id
     * @return 购物车订单 不存在返回null
     */
    private CartOrder findCartOrderByItemId(List<CartOrder> list, long itemId) {
        for (CartOrder cartOrder : list) {
            if (cartOrder.getItem().getId().equals(itemId)) {
                return cartOrder;
            }
        }
        return null;
    }

    /**
     * 根据id返回对应购物车对象
     *
     * @param id 购物车id
     * @return 购物车对象 不存在时返回null
     */
    private Cart getById(Long id) {
        return id == null ? null : cartRepository.findById(id).orElse(null);
    }

    /**
     * 检查购物车中是否已有商品
     *
     * @param itemId 商品id
     * @return true表有 false表无
     */
    public boolean hasItem(Cart cart, long itemId) {
        return findCartOrderByItemId(cart.getOrderList(), itemId) != null;
    }
}
