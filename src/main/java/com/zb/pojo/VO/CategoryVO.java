package com.zb.pojo.VO;

import com.zb.pojo.Category;
import com.zb.pojo.Item;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class CategoryVO extends Category {
  private List<Item> items;
}
