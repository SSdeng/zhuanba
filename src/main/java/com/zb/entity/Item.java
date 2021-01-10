package com.zb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 商品实体
 *
 * @author YeFeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_item")
@DynamicInsert // 动态插入，字段为空时不加入到insert语句
@DynamicUpdate // 动态更新，仅更新改变字段
public class Item extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 2151072683055622844L;

    /**
     * 商品名称,不可为空
     */
    @Column(nullable = false)
    private String itemName;

    /**
     * 商品描述
     */
    private String description = "主人太懒了，没有描述";

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品图片url地址
     */
    private String image;

    /**
     * 商品数量
     */
    private Integer count = 0;

    /**
     * 商品审核状态，0为待审核，1为审核通过
     */
    private Integer status = 0;

    /**
     * 商品销量
     */
    private Integer sales = 0;

    /**
     * 商品新旧程度，10分制
     */
    private Integer level = 0;

    /**
     * 所属用户，设置外键user_id，参照sys_user的id字段
     */
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    /**
     * 商品订单表
     */
    @OneToMany(mappedBy = "item", cascade = {CascadeType.REFRESH})
    private List<UserOrder> orderList;

    /**
     * 商品所有的分类标签，负责维护外键
     */
    @ManyToMany(targetEntity = Category.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "sys_item_category",
            // 当前对象在中间表的外键
            joinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")},
            // 对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private List<Category> categories;
}
