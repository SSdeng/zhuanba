package com.zb.service.impl;

import java.util.Optional;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.zb.entity.User;
import com.zb.exception.MyException;
import com.zb.repository.UserRepository;
import com.zb.service.UserService;

/**
 * 用户服务实现类
 *
 * @author YeFeng
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    /**
     * 注册新用户
     *
     * @param newUser
     *            新增User对象
     * @return 插入后User对象
     */
    @Override
    public User register(User newUser) {
        String newPassword = new Md5Hash(newUser.getPassword(), newUser.getUsername(), 2).toString();
        newUser.setPassword(newPassword);
        userRepository.save(newUser);
        return newUser;
    }

    /**
     * 根据用户id删除用户
     *
     * @param user_id
     *            用户id
     * @return 删除结果
     */
    @Override
    public void deleteById(int user_id) {
        userRepository.deleteById(user_id);
    }

    /**
     * 更新用户信息
     *
     * @param user
     *            需更新User对象
     * @return 更新后User对象
     */
    @Override
    public User updateUserInfo(User user) {
        userRepository.save(user);
        return user;
    }

    /**
     * 登录
     *
     * @param userName
     *            用户名
     * @param password
     *            密码
     * @return 用户名密码匹配则返回对应User对象; 否则返回null
     */
    @Override
    public User login(String userName, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        subject.login(token);
        return (User)subject.getPrincipals().getPrimaryPrincipal();
    }

    /**
     * 返回用户总数
     *
     * @return 用户总数
     */
    @Override
    public long getTotalNumber() {
        return userRepository.count();
    }

    /**
     * 分页查询
     *
     * @param pageNo
     *            起始页码
     * @param pageSize
     *            分页大小
     * @return 用户列表
     */
    @Override
    public Page<User> findAllByPage(int pageNo, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNo - 1, pageSize));
    }

    /**
     * 根据用户id查找用户
     *
     * @param user_id
     *            用户id
     * @return 对应user对象
     */
    @Override
    public User findById(int user_id) {
        Optional<User> user = userRepository.findById(user_id);
        if (!user.isPresent()) {
            throw new MyException("用户未找到");
        }
        return user.get();
    }

    /**
     * 根据用户名查找用户
     *
     * @param userName
     *            用户名
     * @return 对应User对象
     */
    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

}
