package com.zb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.*;

import lombok.*;

/**
 * 求购信息
 *
 * @author lijiacheng
 * @version 1.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_wants")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "update sys_user set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Wants implements Serializable {

    private static final long serialVersionUID = -8037839931624232126L;

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
     * 求购标题 不可为空
     */
    @Column(nullable = false)
    private String title;
    /**
     * 求购描述 默认为空串
     */
    private String description = "";
    /**
     * 求购图片地址
     */
    private String image = "/images/logo2.png";
    /**
     * 求购所属用户
     */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false)
    @JsonIgnoreProperties(value = "wants")
    @ToString.Exclude
    private User user;
    /**
     * 求购评论列表
     */
    @OneToMany(mappedBy = "wants", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JsonIgnoreProperties(value = "wants")
    @ToString.Exclude
    private List<WantsComment> comments;
}
