package com.zb.pojo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Category {
  private int id;
  private String name;
  private int deleted;
}
