package com.zb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author whz
 * @version 1.0
 * 用户收藏
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_collection")
@DynamicInsert // 动态插入，字段为空时不加入到insert语句
@DynamicUpdate // 动态更新，字段为空时不加入到update语句
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
    private User user;
    /**
     * 收藏列表，负责维护外键
     */
    @ManyToMany(targetEntity = Item.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "sys_collection_item",
            // 当前对象在中间表的外键
            joinColumns = {@JoinColumn(name = "collection_id", referencedColumnName = "id")},
            // 对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")})
    private List<Item> items;


}
