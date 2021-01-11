package com.zb.controller;

import com.zb.entity.Item;
import com.zb.entity.User;
import com.zb.entity.UserOrder;
import com.zb.service.ItemService;
import com.zb.service.OrderService;
import com.zb.service.UserService;
import com.zb.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    private UserService userService;
    @Resource
    private ItemService itemService;

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
    public Result addOneOrder(@RequestParam("itemId") Long itemId, @RequestParam("userId") Long userId, @RequestParam("count") int count){
        if(addOrder(userId, itemId, count) == null){
            return Result.error();
        }
        return Result.ok();
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
        if(orderService.getById(orderId) == null){
            return Result.error("Order Not Exist");
        }
        if(orderService.deleteById(orderId)){
            return Result.ok();
        }
        return Result.error("Error Occured");
    }

    /**
     * 购物车结算
     *
     * @param items
     * @return
     */
    @PostMapping("/addcart")
    @ResponseBody
    public Result addMultipleOrder(@RequestParam("items") List<Map<String,String>> items){
        for(Map<String,String> map:items){
            UserOrder order = addOrder(Long.valueOf(map.get("userId")), Long.valueOf(map.get("itemId")), Integer.parseInt(map.get("count")));
            if(order == null){
                return Result.error();
            }
        }
        return Result.ok();
    }

    /**
     * 插入单个订单
     *
     * @param userId 用户id
     * @param itemId 商品id
     * @param count 商品数量
     * @return 结果订单
     */
    private UserOrder addOrder(Long userId, Long itemId, int count){
        User user = userService.findById(userId);
        Item item = itemService.findById(itemId);
        UserOrder order = new UserOrder();
        order.setUser(user);
        order.setItem(item);
        order.setItemCount(count);
        order.setTotalPrice(item.getPrice().multiply(new BigDecimal(count)));
        return orderService.insertOrder(order);
    }

}
