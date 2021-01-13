package com.zb.entity.vo;

import com.zb.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author lijiacheng
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
