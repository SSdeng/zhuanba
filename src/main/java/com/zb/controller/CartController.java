package com.zb.controller;

import com.zb.service.CartService;
import com.zb.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 购物车控制器
 *
 * @author YeFeng
 */
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Resource
    private CartService cartService;


    /**
     * 获取用户购物车
     *
     * @param userId 用户id
     * @return 返回信息
     */
    @GetMapping("all")
    public Result getUserCart(@RequestParam("userId") long userId) {
        return Result.ok(cartService.findCartById(userId));
    }

    /**
     * 添加购物车订单
     *
     * @param userId 用户id
     * @param itemId 商品id
     * @param count  商品数量
     * @return 返回信息
     */
    @PostMapping("add")
    public Result addOrder(@RequestParam("userId") long userId, @RequestParam("userId") long itemId, @RequestParam("count") int count) {
        return Result.ok(cartService.addOrder(userId, itemId, count));
    }

    /**
     * 删除购物车订单
     *
     * @param userId 用户id
     * @param itemId 商品id
     * @return 返回信息
     */
    @PostMapping("remove")
    public Result removeOrder(@RequestParam("userId") long userId, @RequestParam("userId") long itemId) {
        return Result.ok(cartService.removeOrder(userId, itemId));
    }

    /**
     * 修改订单商品数量
     *
     * @param cartId 购物车id
     * @param itemId 商品id
     * @param count  数量
     * @return 返回信息
     */
    @PostMapping("changevalue")
    public Result changeValue(@RequestParam("cartId") long cartId, @RequestParam("userId") long itemId, @RequestParam("count") int count) {
        return Result.ok(cartService.updateOrder(cartId, itemId, count));
    }

    /**
     * 清空购物车
     *
     * @param cartId 购物车id
     * @return 返回信息
     */
    @PostMapping("buy")
    public Result emptyCart(@RequestParam("cartId") long cartId) {
        return Result.ok(cartService.emptyCart(cartId));
    }
}
