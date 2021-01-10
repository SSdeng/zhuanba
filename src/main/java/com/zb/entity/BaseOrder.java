package com.zb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 订单公共属性字段
 *
 * @author lijiacheng
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseOrder extends BaseEntity{

    /**
     * 商品数量
     */
    @Column(nullable = false)
    private Integer itemCount = 1;
    /**
     * 相关商品
     */
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Item item;
}
