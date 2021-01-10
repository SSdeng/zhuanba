package com.zb.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

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
@Entity
@Table(name = "sys_order")
@DynamicInsert // 动态插入，字段为空时不加入到insert语句
@DynamicUpdate // 动态更新，仅更新改变字段
public class UserOrder extends BaseOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 相关商品
     */
    @ManyToOne(targetEntity = Item.class, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Item item;

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