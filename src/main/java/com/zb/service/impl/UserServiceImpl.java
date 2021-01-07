package com.zb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zb.mapper.UserMapper;
import com.zb.pojo.User;
import com.zb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册新用户
     *
     * @param newUser 新增User对象
     * @return 插入后User对象
     */
    @Override
    public User insert(User newUser) {
        userMapper.insertSelective(newUser);
        return newUser;
    }

    /**
     * 根据用户id删除用户
     *
     * @param user_id 用户id
     * @return 删除结果
     */
    @Override
    public boolean deleteById(int user_id) {
        int res = userMapper.deleteByPrimaryKey(user_id);
        if (res > 0) return true;
        else return false;
    }

    /**
     * 更新用户信息
     *
     * @param user 需更新User对象
     * @return 更新后User对象
     */
    @Override
    public User updateUserInfo(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return user;
    }

    /**
     * 查看用户名是否重复
     *
     * @param userName 用户名
     * @return true表重复; false表不重复
     */
    @Override
    public boolean hasDuplicateName(String userName) {
        if (userMapper.findByUsername(userName) == null) return true;
        else return false;
    }

    /**
     * 登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return 用户名密码匹配则返回对应User对象; 否则返回null
     */
    @Override
    public User login(String userName, String password) {
        return userMapper.findByUsernameAndPassword(userName, password);
    }

    /**
     * 返回用户总数
     *
     * @return 用户总数
     */
    @Override
    public int getTotalNumber() {
        return userMapper.count();
    }

    /**
     * 分页查询
     *
     * @param pageNo 起始页码
     * @param pageSize 分页大小
     * @return 用户列表
     */
    @Override
    public PageInfo<User> findPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<User> list = userMapper.selectAll();
        return new PageInfo<>(list);
    }

    /**
     * 根据用户id查找用户
     *
     * @param user_id 用户id
     * @return 对应user对象
     */
    @Override
    public User findById(int user_id) {
        return userMapper.selectByPrimaryKey(user_id);
    }

    /**
     * 根据用户名查找用户
     *
     * @param userName 用户名
     * @return 对应User对象
     */
    @Override
    public User findByUserName(String userName) {
        return userMapper.findByUsername(userName);
    }

    /**
     * 根据学号查找用户
     *
     * @param studentNumber 学号
     * @return 对应User对象
     */
    @Override
    public User findByStudentNumber(String studentNumber) {
        return userMapper.findByStudentNumber(studentNumber);
    }

    /**
     * 根据手机号查找用户
     *
     * @param phoneNumber 手机号
     * @return 对应User对象
     */
    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userMapper.findByPhoneNumber(phoneNumber);
    }
}
