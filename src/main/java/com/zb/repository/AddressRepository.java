package com.zb.repository;

import com.zb.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Address DAO层
 *
 * @author lijiacheng
 * @version 1.0
 */
public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {

    /**
     * 查询对应用户的地址列表
     *
     * @param userId 用户id
     * @return 地址列表
     */
    List<Address> findAllByUser_Id(Long userId);
}
