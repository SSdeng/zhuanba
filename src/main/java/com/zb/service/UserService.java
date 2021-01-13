package com.zb.service;

import com.zb.entity.vo.LoginUserVO;
import org.springframework.data.domain.Page;

import com.zb.entity.User;

/**
 * 用户服务接口
 *
 * @author YeFeng
 */
public interface UserService {
    /**
     * 注册新用户
     * 同时创建对应购物车
     *
     * @param newUser
     *            新增User对象
     * @return 插入后User对象
     */
    User insertSelective(User newUser);

    /**
     * 根据用户id删除用户
     *
     * @param user_id
     *            用户id
     */
    void deleteById(long user_id);

    /**
     * 更新用户信息
     *
     * @param JSONUser
     *            JSON格式的用户信息
     * @param userId
     *            用户id
     * @return 更新后的用户
     */
    User updateUserInfo(String JSONUser, long userId);

    /**
     * 登录
     *
     * @param userName
     *            用户名
     * @param password
     *            密码
     * @return 用户名密码匹配则返回对应User对象; 否则返回null
     */
    LoginUserVO login(String userName, String password);

    /**
     * 返回用户总数
     *
     * @return 用户总数
     */
    long getTotalNumber();

    /**
     * 分页查询
     *
     * @param pageNo
     *            起始页码
     * @param pageSize
     *            分页大小
     * @return 用户列表
     */
    Page<User> findAllByPage(int pageNo, int pageSize);

    /**
     * 根据用户id查找用户
     *
     * @param user_id
     *            用户id
     * @return 对应user对象
     */
    User findById(long user_id);

    /**
     * 根据用户名查找用户
     *
     * @param userName
     *            用户名
     * @return 对应User对象
     */
    User findByUserName(String userName);
}
