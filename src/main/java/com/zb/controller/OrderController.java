package com.zb.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.zb.service.CartService;
import com.zb.service.OrderService;
import com.zb.util.Result;

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
     * @param itemId
     *            订单id
     * @param userId
     *            购买用户id
     * @param count
     *            商品数量
     * @return 操作结果
     */
    @PostMapping("/addone")
    @ResponseBody
    public Result addOneOrder(@RequestParam("itemId") long itemId, @RequestParam("userId") long userId,
        @RequestParam(value = "value", defaultValue = "1") int count) {
        orderService.addOrder(userId, itemId, count);
        return Result.ok("购买成功", null);
    }

    /**
     * 删除指定id的订单
     *
     * @param orderId
     *            订单id
     * @return 删除结果
     */
    @PostMapping("/remove")
    @ResponseBody
    public String removeOrder(@RequestParam("userId") long userId, @RequestParam("orderId") long orderId) {
        orderService.deleteById(orderId);
        return "redirect:/api/user/info?userId" + userId;
    }

    /**
     * 购物车结算
     *
     * @param userId
     * @param maps
     * @return
     */
    @PostMapping("/addcart")
    @ResponseBody
    @Transactional
    public Result addMultipleOrder(@RequestParam("userId") long userId, @RequestBody Map<String, Object> maps) {
        List<Map<String, String>> items = (List<Map<String, String>>)maps.get("items");
        for (Map<String, String> map : items) {
            orderService.addOrder(userId, Long.parseLong(map.get("itemId")), Integer.parseInt(map.get("value")));
            cartService.removeOrder(userId, Long.parseLong(map.get("itemId")));
        }
        return Result.ok("结算成功", null);
    }
}
