package com.zb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.*;

import lombok.*;

/**
 * 求购评论
 *
 * @author lijiacheng
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_wcomment")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "update sys_wcomment set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class WantsComment implements Serializable {

    private static final long serialVersionUID = -6056049233558744367L;

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
     * 评论内容
     */
    @Column(nullable = false)
    private String content;

    /**
     * 所属求购
     */
    @ManyToOne(targetEntity = Wants.class)
    @JoinColumn(name = "wants_id", referencedColumnName = "id", updatable = false)
    @JsonIgnoreProperties(value = "comments")
    @ToString.Exclude
    private Wants wants;
    /**
     * 发表用户
     */
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false)
    @JsonIgnoreProperties(value = "wantsComments")
    @ToString.Exclude
    private User user;

}
