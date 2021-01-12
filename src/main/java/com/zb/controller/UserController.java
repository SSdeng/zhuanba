package com.zb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.zb.entity.User;
import com.zb.enums.ResultEnum;
import com.zb.service.UserService;
import com.zb.util.Result;

/**
 * 用户控制器
 *
 * @author dengzhijian
 * @version 1.0
 **/
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 获取用户信息
     * 
     * @param userId
     *            用户id
     * @return 用户实体
     */
    @GetMapping("/info")
    public User getUserInfo(@RequestParam("userId") long userId) {
        return userService.findById(userId);
    }

    /**
     * 修改用户信息
     * 
     * @param userId
     *            用户id
     * @return Result
     */
    @PostMapping("/info")
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
    public Result login(@RequestBody Map<String, Object> map) {
        String username = (String)map.get("username");
        String password = (String)map.get("password");
        User user = userService.login(username, password);
        Map<String, Object> data = new HashMap<>();
        data.put("userId", user.getId());
        return Result.ok("登录成功", data);
    }

    @PostMapping("/register")
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
    public Result unAuth() {
        return Result.build(ResultEnum.USER_NOT_LOGIN);
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
