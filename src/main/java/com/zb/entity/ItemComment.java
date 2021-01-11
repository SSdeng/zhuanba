package com.zb.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 商品评论
 *
 * @author shenmanjie
 *
 */
@Entity
@Table(name = "sys_icomment")
public class ItemComment extends BaseComment implements Serializable {

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToOne(targetEntity = Item.class)
    @JoinColumn(name = "item_id",referencedColumnName = "id")
    private Item item;

}
