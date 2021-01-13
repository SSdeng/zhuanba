package com.zb.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.*;

/**
 * 购物车
 *
 * @author YeFeng
 * @version 1.0
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_cart")
@DynamicInsert // 动态插入，字段为空时不加入到insert语句
@DynamicUpdate // 动态更新，仅更新改变字段
@SQLDelete(sql = "update sys_cart set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Cart implements Serializable {

    private static final long serialVersionUID = -8790089710781789422L;

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
     * 所属用户
     */
    @OneToOne(targetEntity = User.class)
    @PrimaryKeyJoinColumn
    @JsonIgnoreProperties(value = "cart")
    @EqualsAndHashCode.Exclude
    private User user;

    /**
     * 拥有订单表
     */
    @OneToMany(mappedBy = "cart", cascade = {CascadeType.ALL})
    @JsonIgnoreProperties(value = "cart")
    @EqualsAndHashCode.Exclude
    private List<CartOrder> orderList;

    public Cart(User user) {
        this.user = user;
        this.orderList = new ArrayList<>();
    }
}
