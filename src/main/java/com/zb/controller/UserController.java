package com.zb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.zb.entity.User;
import com.zb.entity.vo.LoginUserVO;
import com.zb.enums.ResultEnum;
import com.zb.service.UserService;
import com.zb.util.Result;

/**
 * 用户控制器
 *
 * @author dengzhijian
 * @version 1.0
 **/
@Controller
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 获取用户信息
     * 
     * @param userId
     *            用户id
     * @return 用户信息
     */
    @GetMapping("/info")
    public ModelAndView getUserInfo(@RequestParam("userId") long userId) {
        ModelAndView modelAndView = new ModelAndView("info");
        User user = userService.findById(userId);

        modelAndView.addObject("user", user);
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
    public Result modifyUserInfo(@RequestBody String JSONUser, @RequestParam("userId") Long userId) {
        userService.updateUserInfo(JSONUser, userId);
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
        return Result.ok("登录成功", data);
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
    public Result unauthorized() {
        return Result.build(ResultEnum.USER_NOT_AUTH);
    }
}
