package com.zb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.*;

/**
 * 订单实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Order implements Serializable {
    private Integer id;
    private Integer status;
    private Integer itemCount;
    private BigDecimal totalPrice;
    private Date createTime;
    private Date updateTime;
    private static final long serialVersionUID = 1L;
    private User user;
    private Item item;

}