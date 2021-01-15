package com.zb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zb.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.zb.elasticsearch.ItemEsRepository;
import com.zb.entity.Item;
import com.zb.entity.User;
import com.zb.exception.MyException;
import com.zb.repository.UserRepository;
import com.zb.service.AdminService;
import com.zb.service.ItemService;
import com.zb.service.UserService;

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

    @Resource
    private ItemRepository itemRepository;

    @Resource
    private ItemEsRepository itemEsRepository;

    /**
     * 修改商品审核状态，放入索引库
     *
     * @param itemId  商品id
     * @param adminId 管理者id
     * @param status  审核结果
     * @return 商品
     */
    @Override
    public Item setAuditStatus(long itemId, long adminId, int status) {
        Item item = itemService.findById(itemId);
        if (item.getStatus() == 1 && status != 1) {
            itemEsRepository.delete(item);
        } else if (item.getStatus() != 1 && status == 1) {
            itemEsRepository.save(item);
        }
        return itemService.setAuditStatus(item, adminId, status);
    }

    /**
     * 删除商品,同时从索引库删除
     *
     * @param itemId 商品id
     * @return 删除结果
     */
    @Override
    public boolean deleteItem(long itemId) {
        itemService.deleteById(itemId);
        itemEsRepository.deleteById(itemId);
        return true;
    }

    /**
     * 分页获取所有商品
     *
     * @param pageNo   起始页码
     * @param pageSize 分页大小
     * @return 分页商品表
     */
    @Override
    public Page<Item> getAllItemsByPage(int pageNo, int pageSize) {
        return itemService.findAllPage(pageNo, pageSize);
    }

    /**
     * 删除用户
     *
     * @param userId 用户id
     */
    @Override
    public void deleteUser(long userId) {
        userService.deleteUserById(userId);
        itemEsRepository.deleteAllByUser_id(userId);
    }

    /**
     * 获取所有用户
     * 包括被封禁用户
     *
     * @return 用户表
     */
    @Override
    public List<User> getAllUser() {
        return userRepository.getAllByRole("user");
    }

    /**
     * 获取所有管理员
     * 包括被封禁的
     *
     * @return 管理员表
     */
    @Override
    public List<User> getAllAdmin() {
        return userRepository.getAllByRole("admin");
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
        if (userService.findById(adminId).getRole().equals("admin")) {
            userRepository.deleteById(adminId);
        } else {
            throw new MyException("非法操作！");
        }
    }

    /**
     * 解禁用户
     *
     * @param userId 用户id
     */
    @Override
    public void unbanUser(long userId) {
        User user = userRepository.findByUserIdFromAll(userId);
        System.out.println(user.toString()+"*****************************************");
        System.out.println(user.getRole()+"\n\n\n\n\n");
        if (!user.getRole().equals("user")) {
            throw new MyException("非法操作！");
        }
        unban(user);
    }

    /**
     * 解禁管理员
     *
     * @param adminId 管理员id
     */
    @Override
    public void unbanAdmin(long adminId) {
        User user = userRepository.findByUserIdFromAll(adminId);
        System.out.println(user.toString()+"*****************************************");
        System.out.println(user.getRole()+"\n\n\n\n\n");
        if (!user.getRole().equals("admin")) {
            throw new MyException("非法操作！");
        }
        unban(user);
    }

    /**
     * 解禁,同时恢复用户所有商品
     *
     * @param user 用户
     */
    private void unban(User user) {
        user.setDeleted(0);
        userRepository.save(user);
        List<Item> list = itemRepository.findByUserIdFromAll(user.getId());
        System.out.println("开始恢复商品！！！！\n\n\n\n");
        for (Item item : list) {
            System.out.println(item.toString());
            item.setDeleted(0);
            itemRepository.save(item);
        }
        itemEsRepository.saveAll(list);
    }
}
