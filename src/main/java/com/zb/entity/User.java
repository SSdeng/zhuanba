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
@ToString
@Entity
@Table(name = "sys_user")
@DynamicInsert // 动态插入，字段为空时不加入到insert语句
@DynamicUpdate // 动态更新，字段为空时不加入到update语句
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -2249088288605052627L;
    @Column(unique = true, nullable = false)
    private String username; // 用户名，唯一，不可为空，
    @Column(nullable = false)
    private String password; // 密码，不可为空
    private String realName; // 真实姓名
    private String phoneNumber; // 电话号码
    private String email; // 邮箱
    private Integer gender = 0; // 性别，0为男性，1为女性
    private String academy; // 学院
    private String grade; // 年级
    private String studentNumber; // 学号
    private String role = "user"; // 角色，用于权限认证
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<Item> items; // 一对多关系，所有全部级联操作都开启，放弃维护权，由item维护外键,
}
