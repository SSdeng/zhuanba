package com.zb.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
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

    final UserService userService;

    /**
     * 构造器依赖注入
     *
     * @param userService
     *            用户服务
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取用户信息
     * 
     * @param userId
     *            用户id
     * @return 用户实体
     */
    @GetMapping("/info")
    public User getUserInfo(@RequestParam("userId") int userId) {
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
    public Result modifyUserInfo(@RequestBody User user, @RequestParam("userId") int userId) {
        if (userService.findById(userId) == null) {
            return Result.error("该用户不存在", null);
        }
        userService.updateUserInfo(user);
        return Result.ok("修改用户信息成功", null);
    }

    /**
     * 处理用户登录
     * 
     * @return Result
     */
    @PostMapping("/login")
    public Result login(@RequestBody Map map) {
        String username = (String)map.get("username");
        String password = (String)map.get("password");
        User user = userService.login(username, password);
        return Result.ok("登录成功", new HashMap<>().put("userId", user.getId()));
    }

    // @GetMapping("/logout")
    // public Result logout() {
    // userService.logout();
    // return Result.ok("注销成功");
    // }
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        String newPassword = new Md5Hash(user.getPassword(), user.getUsername(), 2).toString();
        user.setPassword(newPassword);
        userService.insert(user);
        return Result.ok("注册成功", null);
    }

    @RequestMapping("/un_auth")
    public Result unAuth() {
        return Result.build(ResultEnum.USER_NOT_LOGIN);
    }

    @RequestMapping("/unauthorized")
    public Result unauthorized() {
        return Result.build(ResultEnum.USER_NOT_AUTH);
    }
}
