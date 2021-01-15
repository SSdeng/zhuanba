package com.zb.controller;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.zb.util.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.zb.entity.Address;
import com.zb.entity.User;
import com.zb.entity.vo.LoginUserVO;
import com.zb.enums.ResultEnum;
import com.zb.service.AddressService;
import com.zb.service.UserService;
import com.zb.util.Result;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * 用户控制器
 *
 * @author dengzhijian
 * @version 1.0
 **/
@Controller
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private AddressService addressService;

    /**
     * 获取用户信息
     * 
     * @param userId
     *            用户id
     * @return 用户信息
     */
    @GetMapping("/info")
    public ModelAndView getUserInfo(@RequestParam("userId") String userId) {
        ModelAndView modelAndView = new ModelAndView("info");
        User user = userService.findById(Base64Util.decode(userId));

        modelAndView.addObject("user", user);
        modelAndView.addObject("address", user.getAddresses());
        modelAndView.addObject("collections", user.getCollection().getItems());
        modelAndView.addObject("wants", user.getWants());
        modelAndView.addObject("orders", user.getUserOrders());

        return modelAndView;
    }

    /**
     * 修改用户信息
     * 
     * @param userId
     *            用户id
     * @return Result
     */
    @PostMapping("/info")
    @ResponseBody
    public Result modifyUserInfo(@RequestBody String JSONUser, @RequestParam("userId") String userId) {
        userService.updateUserInfo(JSONUser, Base64Util.decode(userId));
        return Result.ok("修改用户信息成功", null);
    }

    /**
     * 处理用户登录
     * 
     * @return Result
     */
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody Map<String, Object> map) {
        String username = (String)map.get("username");
        String password = (String)map.get("password");
        LoginUserVO loginUser = userService.login(username, password);
        Map<String, Object> data = new HashMap<>();
        data.put("userId", loginUser.getId());
        Result result = Result.ok(data);
        if (loginUser.getRole().equals("user")) {
            result.injectEnum(ResultEnum.USER_LOGIN);
        } else if (loginUser.getRole().equals("admin")) {
            result.injectEnum(ResultEnum.ADMIN_LOGIN);
        } else if (loginUser.getRole().equals("root")) {
            result.injectEnum(ResultEnum.ROOT_LOGIN);
        }
        return result;
    }

    /**
     * 用户注册
     *
     * @param user
     *            用户
     * @return 返回消息
     */
    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestBody User user) {
        userService.insertSelective(user);
        return Result.ok("注册成功", null);
    }

    /**
     * 用户未登录处理
     * 
     * @return Result
     */
    @RequestMapping("/un_auth")
    public String unAuth() {
        return "login";
    }

    /**
     * 用户未授权处理
     * 
     * @return Result
     */
    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "accessDenied";
    }

    /**
     * 新增用户地址
     *
     * @param userId
     *            用户id
     * @param map
     *            地址详情
     * @return 个人主页
     */
    @PostMapping("/addAddress")
    @ResponseBody
    public Result addAddress(@RequestParam("userId") String userId, @RequestBody Map<String, String> map) {
        Address newAddress = new Address();
        newAddress.setDetail(map.get("address"));
        newAddress.setUser(userService.findById(Base64Util.decode(userId)));
        addressService.insertSelective(newAddress);
        return Result.ok("添加成功", null);
    }

    /**
     * 删除用户地址
     *
     * @param addressId
     *            地址id
     * @return 用户主页
     */
    @GetMapping("/delAddress")
    public String deleteAddress(@RequestParam("addressId") long addressId) {
        long userId = addressService.findById(addressId).getUser().getId();
        addressService.deleteById(addressId);
        return "redirect:/api/user/info?userId=" + Base64Util.encode(userId);
    }
}
