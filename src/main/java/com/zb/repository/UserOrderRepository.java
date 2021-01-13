package com.zb.repository;

import com.zb.entity.User;
import com.zb.entity.UserOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * UserOrder DAO层
 *
 * @author lijiacheng
 * @version 1.0
 */
public interface UserOrderRepository extends JpaRepository<UserOrder, Long>, JpaSpecificationExecutor<UserOrder> {

    /**
     * 分页查询对应用户的所有订单
     *
     * @param userId 用户id
     * @param pageable 分页请求
     * @return 分页结果
     */
    @Query("select order from UserOrder order join order.user user where user.id = ?1 ")
    Page<UserOrder> findAllByUser(Long userId, Pageable pageable);

    /**
     * 分页查询对应用户特定状态的所有订单
     *
     * @param userId 用户id
     * @param status 订单状态
     * @param pageable 分页请求
     * @return 分页结果
     */
    @Query("select order from UserOrder order join order.user user where user.id = ?1 and order.status = ?2")
    Page<UserOrder> findAllByStatusAndUser(Long userId, int status, Pageable pageable);
}