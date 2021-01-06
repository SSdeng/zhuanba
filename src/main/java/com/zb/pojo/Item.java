package com.zb.pojo;

import lombok.*;

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
}
