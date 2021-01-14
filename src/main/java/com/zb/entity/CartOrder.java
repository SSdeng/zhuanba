package com.zb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.*;

import lombok.*;

/**
 * 购物车订单
 *
 * @author YeFeng
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_corder")
@DynamicInsert // 动态插入，字段为空时不加入到insert语句
@DynamicUpdate // 动态更新，仅更新改变字段
@SQLDelete(sql = "update sys_corder set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class CartOrder implements Serializable {

    private static final long serialVersionUID = -4331477511607701470L;

    /**
     * 主键，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键，自增

    /**
     * 用于逻辑删除，0为未删除，1为已删除
     */
    private Integer deleted = 0;

    /**
     * 创建时间，执行insert操作时自动更新该字段值
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 修改时间，执行update操作时自动更新该字段值
     */
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    /**
     * 商品数量 不可为空，默认为1
     */
    @Column(nullable = false)
    private Integer itemCount = 1;

    /**
     * 相关商品
     */
    @ManyToOne(targetEntity = Item.class, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "item_id", referencedColumnName = "id", updatable = false)
    private Item item;

    /**
     * 所属购物车
     */
    @ManyToOne(targetEntity = Cart.class, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "cart_id", referencedColumnName = "id", updatable = false)
    @JsonIgnoreProperties(value = "orderList")
    @ToString.Exclude
    private Cart cart;

    public CartOrder(Cart cart, long itemId, int cnt) {
        this.cart = cart;
        this.item = new Item();
        this.item.setId(itemId);
        this.itemCount = cnt;
    }
}
