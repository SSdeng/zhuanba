package com.zb.controller;

import com.zb.entity.User;
import com.zb.repository.UserRepository;
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
@RequestMapping("/api")
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 用户列表页
     *
     * @param model 模型
     * @return 视图
     */
    @GetMapping("/admin/userList")
    public String getUserList(Model model) {
        model.addAttribute("userList", adminService.getAllUser());
        return "admin";
    }

    /**
     * 用户列表页
     *
     * @param model 模型
     * @return 视图
     */
    @GetMapping("/root/userList")
    public String getUserListRoot(Model model) {
        model.addAttribute("userList", adminService.getAllUser());
        return "radmin";
    }


    /**
     * 管理员列表页
     *
     * @param model 模型
     * @return 视图
     */
    @GetMapping("/root/adminList")
    public String getAdminList(Model model) {
        model.addAttribute("adminList", adminService.getAllAdmin());
        return "root";
    }

    /**
     * 商品列表页
     *
     * @param model    模型
     * @param pageNo   起始页码
     * @param pageSize 分页大小
     * @return 视图
     */
    @GetMapping("/admin/itemList")
    public String getItemList(Model model,
                                   @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        model.addAttribute("itemList", adminService.getAllItemsByPage(pageNo, pageSize));
        return "itemList";
    }

    /**
     * 商品列表页
     *
     * @param model    模型
     * @param pageNo   起始页码
     * @param pageSize 分页大小
     * @return 视图
     */
    @GetMapping("/root/itemList")
    public String getItemListModelRoot(Model model,
                                   @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        model.addAttribute("itemList", adminService.getAllItemsByPage(pageNo, pageSize));
        return "ritemList";
    }

    /**
     * 增加管理员账号
     *
     * @param admin 管理员
     * @return 返回消息
     */
    @PostMapping("/root/addAdmin")
    @ResponseBody
    public Result addAdmin(@RequestBody User admin) {
        adminService.addAdmin(admin);
        return Result.ok();
    }

    /**
     * 封禁管理员账号
     *
     * @param adminId 管理员id
     * @return 返回消息
     */
    @PostMapping("/root/banAdmin")
    @ResponseBody
    public Result banAdmin(@RequestParam("adminId") long adminId) {
        adminService.deleteAdmin(adminId);
        return Result.ok();
    }

    /**
     * 封禁用户账号
     *
     * @param userId 用户id
     * @return 返回消息
     */
    @PostMapping("/admin/banUser")
    @ResponseBody
    public Result banUser(@RequestParam("userId") long userId) {
        adminService.deleteUser(userId);
        return Result.ok();
    }

    /**
     * 解禁用户
     *
     * @param userId 用户id
     * @return 返回消息
     */
    @PostMapping("/admin/unbanUser")
    @ResponseBody
    public Result unbanUser(@RequestParam("userId") long userId) {
        adminService.unbanUser(userId);
        return Result.ok();
    }

    /**
     * 解禁管理员
     *
     * @param adminId 管理id
     * @return 返回消息
     */
    @PostMapping("/root/unbanAdmin")
    @ResponseBody
    public Result unbanAdmin(@RequestParam("adminId") long adminId) {
        adminService.unbanAdmin(adminId);
        return Result.ok();
    }

    /**
     * 审核商品
     *
     * @param itemId  商品id
     * @param adminId 审核者id
     * @param status  审核结果
     * @return 返回消息
     */
    @PostMapping("/admin/checkItem")
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
    @PostMapping("/admin/deleteItem")
    @ResponseBody
    public Result deleteItem(@RequestParam("itemId") long itemId) {
        adminService.deleteItem(itemId);
        return Result.ok();
    }
}
