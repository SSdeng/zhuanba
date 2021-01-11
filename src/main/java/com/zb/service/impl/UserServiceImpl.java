package com.zb.service.impl;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zb.entity.User;
import com.zb.exception.MyException;
import com.zb.repository.UserRepository;
import com.zb.service.UserService;

import java.io.IOException;

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
    public User insertSelective(User newUser) {
        if (getById(newUser.getId()) != null) {
            throw new DataIntegrityViolationException("相同id的user已存在");
        }
        String newPassword = new Md5Hash(newUser.getPassword(), newUser.getUsername(), 2).toString();
        newUser.setPassword(newPassword);
        return userRepository.saveAndFlush(newUser);
    }

    /**
     * 根据用户id删除用户
     *
     * @param user_id
     *            用户id
     */
    @Override
    public void deleteById(long user_id) {
        userRepository.deleteById(user_id);
    }

    /**
     * 更新用户信息
     * 
     * @param JSONUser
     *            JSON格式的用户信息
     * @param userId
     *            用户id
     * @return 更新后的用户
     */
    @Override
    public User updateUserInfo(String JSONUser, Integer userId) {
        User dataUser = findById(userId);
        ObjectMapper mapper = new ObjectMapper();
        // 利用jackson相关API，实现非null值的合并更新
        User newUser = null;
        try {
            newUser = mapper.readerForUpdating(dataUser).readValue(JSONUser);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userRepository.save(newUser);
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
    public User findById(long user_id) {
        User user = getById(user_id);
        if (user == null) {
            throw new MyException("用户未找到");
        }
        return user;
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
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new MyException("用户未找到");
        }
        return user;
    }

    /**
     * 获取id对应user
     *
     * @param id user_id
     * @return user对象 不存在返回null
     */
    private User getById(Long id) {
        return id == null ? null : userRepository.findById(id).orElse(null);
    }

}
