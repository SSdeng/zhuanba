package com.zb.service;

import com.zb.entity.Item;
import com.zb.entity.User;
import org.springframework.data.domain.Page;

/**
 * 管理员服务接口
 *
 * @author YeFeng
 */
public interface AdminService {

    /**
     * 修改商品审核状态
     *
     * @param itemId  商品id
     * @param adminId 管理者id
     * @param status  审核结果
     * @return 商品
     */
    Item setAuditStatus(long itemId, long adminId, int status);


    /**
     * 删除商品
     *
     * @param itemId 商品id
     * @return 删除结果
     */
    boolean deleteItem(long itemId);

    /**
     * 分页获取所有商品
     *
     * @param pageNo   起始页码
     * @param pageSize 分页大小
     * @return 分页商品表
     */
    Page<Item> getAllItemsByPage(int pageNo, int pageSize);

    /**
     * 删除用户
     *
     * @param userId 用户id
     */
    void deleteUser(long userId);

    /**
     * 分页获取所有用户
     *
     * @param pageNo   起始页码
     * @param pageSize 分页大小
     * @return 分页用户表
     */
    Page<User> getAllUsersByPage(int pageNo, int pageSize);

    /**
     * 增加管理员
     *
     * @param newAdmin 管理员
     */
    User addAdmin(User newAdmin);

    /**
     * 删除管理员
     *
     * @param adminId 管理员id
     */
    void deleteAdmin(long adminId);

    /**
     * 解禁用户
     *
     * @param userId 用户id
     */
    void unbanUser(long userId);

    /**
     * 解禁管理员
     *
     * @param adminId 管理员id
     */
    void unbanAdmin(long adminId);
}
