package com.zb.repository;

import com.zb.entity.Cart;
import com.zb.entity.CartOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 购物车订单DAO
 *
 * @author YeFeng
 */
public interface CartOrderRepository extends JpaRepository<CartOrder, Long>, JpaSpecificationExecutor<CartOrder> {}
