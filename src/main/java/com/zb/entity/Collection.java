package com.zb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@ToString
@Entity
@Table(name = "user_collection")
@DynamicInsert // 动态插入，字段为空时不加入到insert语句
@DynamicUpdate // 动态更新，字段为空时不加入到update语句
public class Collection implements Serializable {

    @Id
    @Column(name = "collection_name")
    private String collectionName;//收藏名称

    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_Id", referencedColumnName = "id")
    private User user; // 所属用户，设置外键user_id，参照sys_user的id字段
    @ManyToMany(targetEntity = Item.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "sys_collection_item",
            // 当前对象在中间表的外键
            joinColumns = {@JoinColumn(name = "collection_name", referencedColumnName = "collection_name")},
            // 对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")})
    private List<Item> items; // 商品所有的分类标签，负责维护外键
}
