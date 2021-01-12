package com.zb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单实体
 *
 * @author ljjiacheng
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_uorder")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "update sys_uorder set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class UserOrder implements Serializable {

    private static final long serialVersionUID = -162659726395555840L;

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
    private Date createTime;

    /**
     * 修改时间，执行update操作时自动更新该字段值
     */
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "update_time")
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
     * 订单状态 0：未付款， 1：进行中， 2：已完成， 3：已关闭 默认为0
     */
    @Column(nullable = false)
    private Integer status = 0;
    /**
     * 总价格 不可为空
     */
    @Column(nullable = false)
    private BigDecimal totalPrice;
    /**
     * 相关用户
     */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false)
    @JsonIgnoreProperties
    private User user;
}