package com.zb.service;

import com.zb.entity.Cart;
import com.zb.entity.CartOrder;

import java.util.List;

/**
 * 购物车服务接口
 *
 * @author YeFeng
 */
public interface CartService {

    /**
     * 按id查找购物车
     *
     * @param id 购物车/用户id
     * @return 对应购物车
     */
    Cart findCartById(long id);

    /**
     * 添加购物车订单
     *
     * @param userId 用户id
     * @param itemId 商品id
     * @param cnt 数量
     * @return 购物车
     */
    Cart addOrder(long userId, long itemId, int count);

    /**
     * 移除购物车订单
     *
     * @param userId 用户id
     * @param itemId 商品id
     * @return 购物车
     */
    Cart removeOrder(long userId, long itemId);

    /**
     * 修改订单商品数量
     *
     * @param cartId 购物车id
     * @param itemId 商品id
     * @param count 数量
     * @return 购物车
     */
    Cart updateOrder(long cartId, long itemId, int count);

    /**
     * 清空购物车
     *
     * @param cartId 购物车id
     */
    List<CartOrder> emptyCart(long cartId);
}
