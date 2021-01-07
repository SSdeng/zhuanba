package com.zb.pojo;

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
public class Category {
    private int id;
    private String name;
    private int deleted;
    private Date createTime;
    private Date updateTime;
    private List<Item> items;
}
