package com.zb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户收藏
 *
 * @author whz
 * @version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_collection")
@DynamicInsert // 动态插入，字段为空时不加入到insert语句
@DynamicUpdate // 动态更新，字段为空时不加入到update语句
@SQLDelete(sql = "update sys_collection set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Collection  implements Serializable {
    /**
     * 主键，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键，自增
    /**
     * 收藏名称,不可为空
     */
    @Column(name = "collection_name",nullable = false)
    private String collectionName;
    /**
     * 所属用户，设置外键user_id，参照sys_user的id字段
     */
    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_Id", referencedColumnName = "id")
    @JsonIgnoreProperties
    private User user;
    /**
     * 收藏列表，放弃维护权
     */
    @ManyToMany(mappedBy = "collections", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties
    private List<Item> items;


}
