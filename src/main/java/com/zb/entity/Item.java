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
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "sys_item")
@DynamicInsert // 动态插入，字段为空时不加入到insert语句
@DynamicUpdate // 动态更新，字段为空时不加入到update语句
public class Item extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 2151072683055622844L;
    @Column(nullable = false)
    private String name; // 商品名称,不可为空
    private String description = "主人太懒了，没有描述"; // 商品描述
    private BigDecimal price; // 商品价格
    private String image; // 商品图片url地址
    private Integer count = 0; // 商品数量
    private Integer status = 0; // 商品审核状态，0为待审核，1为审核通过
    private Integer sales = 0; // 商品销量
    private Integer level = 0; // 商品新旧程度，10分制
    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user; // 所属用户，设置外键user_id，参照sys_user的id字段
    @ManyToMany(targetEntity = Category.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "sys_item_category",
        // 当前对象在中间表的外键
        joinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")},
        // 对方对象在中间表的外键
        inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private List<Category> categories; // 商品所有的分类标签，负责维护外键
}
