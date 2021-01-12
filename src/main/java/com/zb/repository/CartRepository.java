package com.zb.repository;

import com.zb.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 购物车DAO
 *
 * @author YeFeng
 */
public interface CartRepository extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart> {}
