package com.zb.service;

import com.zb.entity.Cart;
import com.zb.entity.vo.CartOrderVO;

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
    Cart findCartById(Long id);

    /**
     * 检查购物车中是否已有商品
     *
     * @param id 购物车/用户id
     * @param itemId 商品id
     * @return true表有 false表无
     */
    boolean hasItem(long id, long itemId);

    /**
     * 添加购物车订单
     *
     * @param userId 用户id
     * @param itemId 商品id
     * @param count 数量
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
     * 获取购物车订单表
     *
     * @param cartId 购物车id
     */
    List<CartOrderVO> getCartOrderVoList(long cartId);

}
