package com.zb.entity.vo;

import com.zb.entity.Item;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lijiacheng
 * @version 1.0
 */
@Data
public class ItemVO {
    /**
     * 创建用户的id
     */
    private Long userId;
    /**
     * 商品名
     */
    private String itemName;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 商品新旧程度
     */
    private int level;
    /**
     * 商品单价
     */
    private BigDecimal price;
    /**
     * 商品总量
     */
    private int count;

    public ItemVO(Long userId, String itemName, String description, int level, BigDecimal price, int count){
        this.userId = userId;
        this.itemName = itemName;
        this.description = description;
        this.level = level;
        this.price = price;
        this.count = count;
    }

}
