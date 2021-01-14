package com.zb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户收藏
 *
 * @author whz
 * @version 1.0
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_collection")
@DynamicInsert // 动态插入，字段为空时不加入到insert语句
@DynamicUpdate // 动态更新，字段为空时不加入到update语句
@SQLDelete(sql = "update sys_collection set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Collection implements Serializable {

    private static final long serialVersionUID = -8790089710781789422L;

    /**
     * 主键，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
     * 所属用户，设置外键user_id，参照sys_user的id字段
     */
    @OneToOne(targetEntity = User.class)
    @PrimaryKeyJoinColumn
    @JsonIgnoreProperties(value = "collection")
    @ToString.Exclude
    private User user;

    /**
     * 拥有商品表
     */
    @OneToMany(mappedBy = "collection", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JsonIgnoreProperties(value = "collection")
    @ToString.Exclude
    private List<Item> items;

    public Collection(User user) {
        this.user = user;
        this.items = new ArrayList<>();
    }

}
