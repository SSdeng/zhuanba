package com.zb.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 订单实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamicInsert // 动态插入，字段为空时不加入到insert语句
@DynamicUpdate // 动态更新，字段为空时不加入到update语句
public class Order extends BaseEntity implements Serializable {
    private Integer status;
    private Integer itemCount;
    private BigDecimal totalPrice;
    private static final long serialVersionUID = 1L;
    private User user;
    private Item item;

}