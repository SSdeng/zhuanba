package com.zb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车
 *
 * @author YeFeng
 * @version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_cart")
@DynamicInsert // 动态插入，字段为空时不加入到insert语句
@DynamicUpdate // 动态更新，仅更新改变字段
public class Cart extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -8790089710781789422L;

    /**
     * 所属用户
     */
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false)
    private User user;

    /**
     * 拥有订单表
     */
    @OneToMany(mappedBy = "cart", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<CartOrder> orderList;

    public Cart(User user) {
        this.user = user;
        this.orderList = new ArrayList<>();
    }
}
