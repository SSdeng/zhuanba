package com.zb.pojo;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Item {
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
  private Date creatTime;
  private Date updateTime;
  private User user;
  private List<Category> categories;
}
