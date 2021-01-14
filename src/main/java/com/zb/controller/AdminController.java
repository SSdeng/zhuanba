package com.zb.controller;

import com.zb.entity.User;
import com.zb.service.AdminService;
import com.zb.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 管理员控制器
 *
 * @author YeFeng
 */
@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 用户列表页
     *
     * @param model    模型
     * @param pageNo   起始页码
     * @param pageSize 分页大小
     * @return 视图
     */
    @GetMapping("userList")
    public String getUserList(Model model,
                              @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        model.addAttribute("userList", adminService.getAllUsersByPage(pageNo, pageSize));
        return "userList";
    }

    /**
     * 商品列表页
     *
     * @param model    模型
     * @param pageNo   起始页码
     * @param pageSize 分页大小
     * @return 视图
     */
    @GetMapping("itemList")
    public String getItemListModel(Model model,
                                   @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        model.addAttribute("itemList", adminService.getAllItemsByPage(pageNo, pageSize));
        return "itemList";
    }

    /**
     * 增加管理员账号
     *
     * @param admin 管理员
     * @return 返回消息
     */
    @PostMapping("addAdmin")
    @ResponseBody
    public Result addAdmin(@RequestBody User admin) {
        adminService.addAdmin(admin);
        return Result.ok();
    }

    /**
     * 删除管理员账号
     *
     * @param adminId 管理员id
     * @return 返回消息
     */
    @PostMapping("deleteAdmin")
    @ResponseBody
    public Result deleteAdmin(@RequestParam("adminId") long adminId) {
        adminService.deleteAdmin(adminId);
        return Result.ok();
    }

    /**
     * 删除用户账号
     *
     * @param userId 用户id
     * @return 返回消息
     */
    @PostMapping("deleteUser")
    @ResponseBody
    public Result deleteUser(@RequestParam("userId") long userId) {
        adminService.deleteAdmin(userId);
        return Result.ok();
    }

    /**
     * 审核商品
     *
     * @param itemId 商品id
     * @param adminId 审核者id
     * @param status 审核结果
     * @return 返回消息
     */
    @PostMapping("checkItem")
    @ResponseBody
    public Result checkItem(@RequestParam("itemId") long itemId, @RequestParam("adminId") long adminId, @RequestParam("status") int status) {
        adminService.setAuditStatus(itemId, adminId, status);
        return Result.ok();
    }

    /**
     * 删除商品
     *
     * @param itemId 商品id
     * @return 返回消息
     */
    @PostMapping("deleteItem")
    @ResponseBody
    public Result deleteItem(@RequestParam("itemId") long itemId) {
        adminService.deleteItem(itemId);
        return Result.ok();
    }
}