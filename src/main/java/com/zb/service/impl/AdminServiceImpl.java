package com.zb.service.impl;

import com.zb.entity.Item;
import com.zb.entity.User;
import com.zb.repository.UserRepository;
import com.zb.service.AdminService;
import com.zb.service.ItemService;
import com.zb.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理员服务实现类
 *
 * @author YeFeng
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private ItemService itemService;

    @Resource
    private UserService userService;

    @Resource
    private UserRepository userRepository;

    /**
     * 修改商品审核状态
     *
     * @param itemId 商品id
     * @param adminId 管理者id
     * @param status 审核结果
     * @return 商品
     */
    @Override
    public Item setAuditStatus(long itemId, long adminId, int status) {
        return itemService.setAuditStatus(itemId, adminId, status);
    }

    /**
     * 删除商品
     *
     * @param itemId 商品id
     * @return 删除结果
     */
    @Override
    public boolean deleteItem(long itemId) {
        return itemService.deleteById(itemId);
    }

    /**
     * 分页获取所有商品
     *
     * @param pageNo 起始页码
     * @param pageSize 分页大小
     * @return 分页商品表
     */
    @Override
    public Page<Item> getAllItemsByPage(int pageNo, int pageSize) {
        return itemService.findAllByPage(pageNo, pageSize);
    }

    /**
     * 删除用户
     *
     * @param userId 用户id
     */
    @Override
    public void deleteUser(long userId) {
        userService.deleteUserById(userId);
    }

    /**
     * 分页获取所有用户
     *
     * @param pageNo   起始页码
     * @param pageSize 分页大小
     * @return 分页用户表
     */
    @Override
    public Page<User> getAllUsersByPage(int pageNo, int pageSize) {
        return userService.findAllByPage(pageNo, pageSize);
    }

    /**
     * 增加管理员
     *
     * @param newAdmin 管理员
     */
    @Override
    public User addAdmin(User newAdmin) {
        newAdmin.setRole("admin");
        return userService.insertSelective(newAdmin);
    }

    /**
     * 删除管理员
     *
     * @param adminId 管理员id
     */
    @Override
    public void deleteAdmin(long adminId) {
        userRepository.deleteById(adminId);
    }
}
