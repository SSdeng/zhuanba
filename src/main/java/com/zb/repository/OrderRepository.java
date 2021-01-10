package com.zb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zb.entity.UserOrder;

/**
 * UserOrder DAO层
 */
public interface OrderRepository extends JpaRepository<UserOrder, Long>, JpaSpecificationExecutor<UserOrder> {

}