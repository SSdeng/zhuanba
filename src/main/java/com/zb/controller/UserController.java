package com.zb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zb.pojo.User;
import com.zb.service.UserService;
import com.zb.vo.Result;

/**
 * @author dengzhijian
 * @version 1.0
 **/
@RestController
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 获取用户信息
     * 
     * @param userId
     *            用户id
     * @return 用户实体
     */
    @GetMapping("/api/user/info")
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
    @PostMapping("/api/user/info")
    public Result modifyUserInfo(@RequestBody User user, @RequestParam("userId") int userId) {
        if (userService.findById(userId) == null) {
            return Result.error("该用户不存在");
        }
        userService.updateUserInfo(user);
        return Result.ok("修改用户信息成功");
    }
}
