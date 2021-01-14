package com.zb.controller;

import com.zb.entity.vo.CartOrderVO;
import com.zb.service.CartService;
import com.zb.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车控制器
 *
 * @author YeFeng
 */
@Controller
@RequestMapping("/api/cart")
public class CartController {

    @Resource
    private CartService cartService;


    /**
     * 获取用户购物车
     *
     * @param model 模型
     * @param userId 用户id
     * @return 视图
     */
    @GetMapping("all")
    public String getUserCart(Model model, @RequestParam("userId") long userId) {
        model.addAttribute(cartService.findCartById(userId));
        return "cart";
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
    @ResponseBody
    public Result addOrder(@RequestParam("userId") long userId, @RequestParam("itemId") long itemId, @RequestParam("count") int count) {
        cartService.addOrder(userId, itemId, count);
        return Result.ok();
    }

    /**
     * 删除购物车订单
     *
     * @param userId 用户id
     * @param itemId 商品id
     * @return 返回信息
     */
    @PostMapping("remove")
    @ResponseBody
    public Result removeOrder(@RequestParam("userId") long userId, @RequestParam("itemId") long itemId) {
        cartService.removeOrder(userId, itemId);
        return Result.ok();
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
    @ResponseBody
    public Result changeValue(@RequestParam("cartId") long cartId, @RequestParam("userId") long itemId, @RequestParam("count") int count) {
        cartService.updateOrder(cartId, itemId, count);
        return Result.ok();
    }

    /**
     * 清空购物车
     *
     * @param model 模型
     * @param cartId 购物车id
     * @return 视图
     */
    @PostMapping("buy")
    public String emptyCart(Model model, @RequestParam("cartId") long cartId) {
        model.addAllAttributes(cartService.getCartOrderVoList(cartId));
        return "buy";
    }
}
