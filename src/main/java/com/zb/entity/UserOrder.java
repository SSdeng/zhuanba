package com.zb.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
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
public class UserOrder extends BaseOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 订单状态
     * 0：未付款， 1：进行中， 2：已完成， 3：已关闭
     */
    @Column(nullable = false)
    private Integer status = 0;
    /**
     * 总价格
     */
    @Column(nullable = false)
    private BigDecimal totalPrice;
    /**
     *
     */
    private User user;

}