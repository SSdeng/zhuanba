package com.zb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Document(indexName = "item")
@DynamicInsert // 动态插入，字段为空时不加入到insert语句
@DynamicUpdate // 动态更新，仅更新改变字段
@SQLDelete(sql = "update sys_item set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Item implements Serializable {

    private static final long serialVersionUID = -5566211160135800001L;

    /**
     * 主键，自增
     */
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键，自增

    /**
     * 用于逻辑删除，0为未删除，1为已删除
     */
    @Field(type = FieldType.Integer)
    private Integer deleted = 0;

    /**
     * 创建时间，执行insert操作时自动更新该字段值
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Field(type = FieldType.Date)
    private Date createTime;

    /**
     * 修改时间，执行update操作时自动更新该字段值
     */
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Field(type = FieldType.Date)
    private Date updateTime;

    /**
     * 商品名称,不可为空
     */
    @Column(nullable = false)
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String itemName;

    /**
     * 商品描述
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String description = "主人太懒了，没有描述";

    /**
     * 商品价格
     */
    @Field(type = FieldType.Double)
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
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false)
    @JsonIgnoreProperties(value = "items")
    @ToString.Exclude
    private User user;

    /**
     * 商品订单表
     */
    @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties(value = "item")
    @ToString.Exclude
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
    @JsonIgnoreProperties(value = "items")
    @ToString.Exclude
    private List<Category> categories;

    /**
     * 所属收藏
     */
    @ManyToOne(targetEntity = Collection.class, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "collection_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = "items")
    @ToString.Exclude
    private Collection collection;

    /**
     * 商品评论列表
     */
    @OneToMany(mappedBy = "item", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JsonIgnoreProperties(value = "item")
    @ToString.Exclude
    private List<ItemComment> itemComments;
}
