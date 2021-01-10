package com.zb.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 订单实体
 *
 * @author ljjiacheng
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "sys_order")
@DynamicInsert // 动态插入
@DynamicUpdate // 动态更新
public class Order extends BaseEntity implements Serializable {
    private Integer status;
    private Integer itemCount;
    private BigDecimal totalPrice;
    private static final long serialVersionUID = 1L;
    private User user;
    private Item item;

}