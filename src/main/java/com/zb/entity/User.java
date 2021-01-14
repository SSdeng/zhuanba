package com.zb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

/**
 * 用户实体
 *
 * @author dengzhijian
 * @version 1.1
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_user")
@DynamicInsert // 动态插入，字段为空时不加入到insert语句
@DynamicUpdate // 动态更新，仅更新改变字段
@SQLDelete(sql = "update sys_user set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class User implements Serializable {

    private static final long serialVersionUID = 6224633281865581627L;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间，执行update操作时自动更新该字段值
     */
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 用户名 唯一，不可为空
     */
    @Column(unique = true, nullable = false)
    private String username;
    /**
     * 密码 不可为空
     */
    @Column(nullable = false)
    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 电话号码
     */
    private String phoneNumber;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别 0为男性，1为女性，默认为0
     */
    private Integer gender = 0;
    /**
     * 学院
     */
    private String academy;
    /**
     * 年级
     */
    private String grade;
    /**
     * 学号
     */
    private String studentNumber;
    /**
     * 角色 用于权限认证
     */
    private String role = "user";
    /**
     * 用户购物车
     */
    @OneToOne(cascade = {CascadeType.ALL})
    @PrimaryKeyJoinColumn
    @JsonIgnoreProperties(value = "user")
    @ToString.Exclude
    @org.springframework.data.annotation.Transient
    private Cart cart;
    /**
     * 用户收藏夹
     */
    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL})
    @PrimaryKeyJoinColumn
    @JsonIgnoreProperties(value = "user")
    @ToString.Exclude
    @org.springframework.data.annotation.Transient
    private Collection collection;
    /**
     * 用户地址表
     */
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JsonIgnoreProperties(value = "user")
    @ToString.Exclude
    @org.springframework.data.annotation.Transient
    private List<Address> addresses;
    /**
     * 用户发布商品列表
     */
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JsonIgnoreProperties(value = "user")
    @ToString.Exclude
    @org.springframework.data.annotation.Transient
    private List<Item> items; // 一对多关系，所有全部级联操作都开启，放弃维护权，由item维护外键,
    /**
     * 用户订单列表
     */
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JsonIgnoreProperties(value = "user")
    @ToString.Exclude
    @org.springframework.data.annotation.Transient
    private List<UserOrder> userOrders;
    /**
     * 用户求购项列表
     */
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JsonIgnoreProperties(value = "user")
    @ToString.Exclude
    @org.springframework.data.annotation.Transient
    private List<Wants> wants;
    /**
     * 用户求购评论列表
     */
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JsonIgnoreProperties(value = "user")
    @ToString.Exclude
    @org.springframework.data.annotation.Transient
    private List<WantsComment> wantsComments;

    /**
     * 商品评论列表
     */
    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JsonIgnoreProperties(value = "user")
    @ToString.Exclude
    @org.springframework.data.annotation.Transient
    private List<ItemComment> itemComments;
}
