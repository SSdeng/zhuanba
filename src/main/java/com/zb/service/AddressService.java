package com.zb.service;

import com.zb.entity.Address;

import java.util.List;

/**
 * 地址服务接口
 *
 * @author lijiacheng
 * @version 1.0
 */
public interface AddressService {

    /**
     * 新增地址
     *
     * @param newAddress 新地址
     * @return 新地址
     */
    Address insertSelective(Address newAddress);

    /**
     * 通过id删除地址
     *
     * @param addressId 地址id
     * @return 删除结果
     */
    boolean deleteById(Long addressId);

    /**
     * 更新地址
     *
     * @param json json字符串
     * @param addressId 地址id
     * @return 更新后地址
     */
    Address updateAddress(String json, Long addressId);

    /**
     * 通过id查找地址
     *
     * @param addressId 地址id
     * @return 查到的地址
     */
    Address findById(Long addressId);

    /**
     * 根据用户id获取地址列表
     *
     * @param userId 用户id
     * @return 地址列表
     */
    List<Address> getAllByUserId(Long userId);
}
