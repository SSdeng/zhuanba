package com.zb.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.*;

/**
 * 用户实体
 * 
 * @author dengzhijian
 * @version 1.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_user")
@DynamicInsert // 动态插入，字段为空时不加入到insert语句
@DynamicUpdate // 动态更新，仅更新改变字段
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -2249088288605052627L;
    /**
     * 用户名
     *  唯一，不可为空
     */
    @Column(unique = true, nullable = false)
    private String username;
    /**
     * 密码
     *  不可为空
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
     * 性别
     *  0为男性，1为女性，默认为0
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
     * 角色
     *  用于权限认证
     */
    private String role = "user";
    /**
     * 用户地址表
     */
    @OneToMany(targetEntity = Address.class, mappedBy = "user",cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<Address> addresses;
    /**
     * 用户发布商品列表
     */
    @OneToMany(targetEntity = Item.class, mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<Item> items; // 一对多关系，所有全部级联操作都开启，放弃维护权，由item维护外键,
    /**
     * 用户订单列表
     */
    @OneToMany(targetEntity = UserOrder.class, mappedBy = "user",cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<UserOrder> userOrders;
    /**
     * 用户求购项列表
     */
    @OneToMany(targetEntity = Wants.class, mappedBy = "user",cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<Wants> wants;
    /**
     * 用户求购评论列表
     */
    @OneToMany(targetEntity = WantsComment.class, mappedBy = "user",cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<WantsComment> wantsComments;
}
