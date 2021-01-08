package com.zb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.*;

/**
 * 分类实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Category implements Serializable {
    private static final long serialVersionUID = -679460945000584109L;
    private int id;
    private String name;
    private int deleted;
    private Date createTime;
    private Date updateTime;
    private List<Item> items;
}
