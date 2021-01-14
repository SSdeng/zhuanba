package com.zb.controller;

import com.zb.entity.UserOrder;
import com.zb.service.CartService;
import com.zb.service.OrderService;
import com.zb.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 订单控制器
 *
 * @author lijiacheng
 * @version 1.0
 */
@Controller
@RequestMapping("/api/order")
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private CartService cartService;

    /**
     * 提交订单
     *
     * @param itemId 订单id
     * @param userId 购买用户id
     * @param count 商品数量
     * @return 操作结果
     */
    @PostMapping("/addone")
    @ResponseBody
    public Result addOneOrder(@RequestParam("itemId") long itemId,
                              @RequestParam("userId") long userId,
                              @RequestParam(value = "value", defaultValue = "1") int count){
        orderService.addOrder(userId, itemId, count);
        return Result.ok("购买成功", null);
    }

    /**
     * 删除指定id的订单
     *
     * @param orderId 订单id
     * @return 删除结果
     */
    @PostMapping("/remove")
    @ResponseBody
    public Result removeOrder(@RequestParam("orderId") Long orderId){
        orderService.deleteById(orderId);
        return Result.ok("删除成功", null);
    }

    /**
     * 购物车结算
     *
     * @param items
     * @return
     */
    @PostMapping("/addcart")
    @ResponseBody
    @Transactional
    public Result addMultipleOrder(@RequestParam("userId") long userId, @RequestBody Map<String, Object>[] items){

        for(Map<String,Object> map : items){
            cartService.removeOrder(userId, (long) map.get("itemId"));
            UserOrder order = orderService.addOrder(userId, (long) map.get("itemId"), (int) map.get("value"));
        }
        return Result.ok("结算成功", null);
    }
}
