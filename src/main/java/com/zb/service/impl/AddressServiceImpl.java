package com.zb.service.impl;

import com.zb.entity.Address;
import com.zb.exception.MyException;
import com.zb.repository.AddressRepository;
import com.zb.service.AddressService;
import com.zb.util.JsonTransfer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 地址服务
 *
 * @author lijiacheng
 * @version 1.0
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressRepository addressRepository;

    /**
     * 新增地址
     *
     * @param newAddress 新地址
     * @return 新地址
     */
    @Override
    public Address insertSelective(Address newAddress) {
        return addressRepository.saveAndFlush(newAddress);
    }

    /**
     * 通过id删除地址
     *
     * @param addressId 地址id
     * @return 删除结果
     */
    @Override
    public boolean deleteById(Long addressId) {
        addressRepository.deleteById(addressId);
        return true;
    }

    /**
     * 更新地址
     *
     * @param json      json字符串
     * @param addressId 地址id
     * @return 更新后地址
     */
    @Override
    public Address updateAddress(String json, Long addressId) {
        Address address = findById(addressId);
        address = JsonTransfer.updateSelective(json, address);
        return addressRepository.save(address);
    }

    /**
     * 通过id查找地址
     *
     * @param addressId 地址id
     * @return 查到的地址
     */
    @Override
    public Address findById(Long addressId) {
        Address address = addressRepository.findById(addressId).orElse(null);
        if(address == null){
            throw new MyException("待查找地址不存在");
        }
        return address;
    }

    /**
     * 根据用户id获取地址列表
     *
     * @param userId 用户id
     * @return 地址列表
     */
    @Override
    public List<Address> getAllByUserId(Long userId) {
        return addressRepository.findAllByUser(userId);
    }
}
