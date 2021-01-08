package com.zb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.*;

/**
 * 商品实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Item implements Serializable {
    private static final long serialVersionUID = 2151072683055622844L;
    private int id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private int count;
    private int deleted;
    private int status;
    private int sales;
    private int level;
    private Date createTime;
    private Date updateTime;
    private User user;
    private List<Category> categories;
}
