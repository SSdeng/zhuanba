package com.zb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zb.pojo.User;
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
     * @param userService 用户服务
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @return 用户实体
     */
    @GetMapping("/info")
    public User getUserInfo(@RequestParam("userId") int userId) {
        return userService.findById(userId);
    }

    /**
     * 修改用户信息
     *
     * @param userId 用户id
     * @return Result
     */
    @PostMapping("/info")
    public Result modifyUserInfo(@RequestBody User user, @RequestParam("userId") int userId) {
        if (userService.findById(userId) == null) {
            return Result.error("该用户不存在");
        }
        userService.updateUserInfo(user);
        return Result.ok("修改用户信息成功");
    }

    /**
     * 处理用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Map map) {
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        User user = userService.login(username, password);
        return Result.ok("登录成功", new HashMap<>().put("userId", user.getId()));
    }

    /*
    @GetMapping("/logout")
    public Result logout() {
        userService.logout();
        return Result.ok("注销成功");
    }
    */

    /**
     * 处理用户注册
     *
     * @param user 用户
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        userService.insert(user);
        return Result.ok("注册成功");
    }

    @RequestMapping("/un_auth")
    public Result unAuth() {
        Result result = Result.build();
        result.setCode(2);
        result.setMsg("用户未登录");
        return result;
    }

    @RequestMapping("/unauthorized")
    public Result unauthorized() {
        Result result = Result.build();
        result.setCode(3);
        result.setMsg("用户无权限！");
        return result;
    }
}
