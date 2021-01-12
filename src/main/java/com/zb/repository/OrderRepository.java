package com.zb.repository;

import com.zb.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zb.entity.UserOrder;

/**
 * UserOrder DAO层
 */
public interface OrderRepository extends JpaRepository<UserOrder, Long>, JpaSpecificationExecutor<UserOrder> {
    Page<UserOrder> findByStatusAndUser(Integer status, User user, Pageable pageable);
}