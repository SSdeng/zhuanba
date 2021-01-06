package com.zb.service;

import com.github.pagehelper.PageInfo;
import com.zb.pojo.User;

/**
 * 用户服务接口
 */
public interface UserService {
    /**
     * 注册新用户
     *
     * @param newUser 新增User对象
     * @return 插入后User对象
     */
    User insert(User newUser);

    /**
     * 根据用户id删除用户
     *
     * @param user_id 用户id
     * @return 删除结果
     */
    boolean deleteById(int user_id);

    /**
     * 更新用户信息
     *
     * @param user 需更新User对象
     * @return 更新后User对象
     */
    User updateUserInfo(User user);

    /**
     * 查看用户名是否重复
     *
     * @param userName 用户名
     * @return true表重复; false表不重复
     */
    boolean hasDuplicateName(String userName);

    /**
     * 登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return 用户名密码匹配则返回对应User对象; 否则返回null
     */
    User login(String userName, String password);

    /**
     * 返回用户总数
     *
     * @return 用户总数
     */
    int getTotalNumber();

    /**
     * 分页查询
     *
     * @param pageNo 起始页码
     * @param pageSize 分页大小
     * @return 用户列表
     */
    PageInfo<User> findPage(int pageNo, int pageSize);

    /**
     * 根据用户id查找用户
     *
     * @param user_id 用户id
     * @return 对应user对象
     */
    User findById(int user_id);

    /**
     * 根据用户名查找用户
     *
     * @param userName 用户名
     * @return 对应User对象
     */
    User findByUserName(String userName);

    /**
     * 根据学号查找用户
     *
     * @param schoolNumber 学号
     * @return 对应User对象
     */
    User findBySchoolNumber(String schoolNumber);

    /**
     * 根据手机号查找用户
     *
     * @param phoneNumber 手机号
     * @return 对应User对象
     */
    User findByPhoneNumber(String phoneNumber);
}
