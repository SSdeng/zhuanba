package com.zb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 购物车订单
 *
 * @author YeFeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_corder")
@DynamicInsert // 动态插入，字段为空时不加入到insert语句
@DynamicUpdate // 动态更新，仅更新改变字段
public class CartOrder extends BaseOrder implements Serializable {

    private static final long serialVersionUID = -4331477511607701470L;

    /**
     * 相关商品
     */
    @ManyToOne(targetEntity = Item.class, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Item item;

    /**
     * 所属购物车
     */
    @ManyToOne(targetEntity = Cart.class, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "cart_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Cart cart;
}
