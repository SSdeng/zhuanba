package com.zb.mapper;

import com.zb.entity.Order;

/**
 *  Order DAO层
 */
public interface OrderMapper {
    /**
     * 根据主键删除订单（真删除）
     *
     * @param id 订单id
     * @return 删除结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新订单
     *
     * @param record 要插入的订单
     * @return 插入结果
     */
    int insert(Order record);

    /**
     * 插入新订单（不插入空字段）
     *
     * @param record 要插入的订单
     * @return 插入结果
     */
    int insertSelective(Order record);

    /**
     * 根据主键查找订单
     *
     * @param id 订单id
     * @return 订单实体
     */
    Order selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新订单（不更新空字段）
     *
     * @param record 要更新的订单
     * @return 更新结果
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * 根据主键更新订单
     * @param record 要更新的订单
     * @return 更新结果
     */
    int updateByPrimaryKey(Order record);
}