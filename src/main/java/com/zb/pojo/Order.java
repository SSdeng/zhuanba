package com.zb.pojo;

import java.io.Serializable;
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
    private Double totalPrice;
    private Date createTime;
    private Date updateTime;
    private static final long serialVersionUID = 1L;
    private User user;
    private Item item;

}