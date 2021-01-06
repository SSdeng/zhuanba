package com.zb.pojo.VO;

import com.zb.pojo.Category;
import com.zb.pojo.Item;
import com.zb.pojo.User;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ItemVO extends Item {
  private User user;
  private List<Category> categories;
}
