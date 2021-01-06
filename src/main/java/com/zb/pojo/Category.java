package com.zb.pojo;

import lombok.*;

import java.util.Date;
import java.util.List;

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
