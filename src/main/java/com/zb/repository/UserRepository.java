package com.zb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zb.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * UserDAO层
 * 
 * @author YeFeng
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    /**
     * 按用户名查找用户
     *
     * @param userName 用户名
     * @return 用户对象
     */
    User findByUsername(String userName);

    /**
     * 分页返回所有用户
     * 包括封禁用户
     *
     * @param role 角色
     * @return 分页用户表
     */
    @Query(value = "SELECT * FROM sys_user WHERE role = ?1", nativeQuery = true)
    List<User> getAllByRole(String role);

    /**
     * 按用户名查找用户
     * 包括封禁用户
     *
     * @param username 用户名
     * @return 用户对象
     */
    @Query(value = "SELECT * FROM sys_user WHERE username = ?1", nativeQuery = true)
    User findByUsernameFromAll(String username);

    /**
     * 按用户id查找用户
     * 包括封禁用户
     *
     * @param userId 用户id
     * @return 用户对象
     */
    @Query(value = "SELECT * FROM sys_user WHERE id = ?1", nativeQuery = true)
    User findByUserIdFromAll(long userId);
}