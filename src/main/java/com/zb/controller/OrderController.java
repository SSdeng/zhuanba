package com.zb.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zb.service.ItemService;
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
    private ItemService itemService;
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
        if(itemService.findById(itemId).getCount() == 0){
            return Result.error("购买失败，商品库存为空");
        }
        orderService.addOrder(userId, itemId, count);
        return Result.ok("购买成功", null);
    }

    /**
     * 购物车结算
     *
     * @param userId 用户id
     * @param maps 参数对象
     * @return Result
     */
    @PostMapping("/addcart")
    @ResponseBody
    @Transactional
    public Result addMultipleOrder(@RequestParam("userId") long userId, @RequestBody Map<String, Object> maps) {
        List<Map<String, String>> items = (List<Map<String, String>>)maps.get("items");
        for (Map<String, String> map : items) {
            long itemId = Long.parseLong((map.get("itemId")));
            if(itemService.findById(itemId).getCount() == 0){
                return Result.error("购买失败，商品库存已空", null);
            }
            orderService.addOrder(userId, Long.parseLong(map.get("itemId")), Integer.parseInt(map.get("value")));
            cartService.removeOrder(userId, Long.parseLong(map.get("itemId")));
        }
        return Result.ok("结算成功", null);
    }
}
