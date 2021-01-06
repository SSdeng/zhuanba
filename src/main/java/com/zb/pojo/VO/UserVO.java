package com.zb.pojo.VO;

import com.zb.pojo.User;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserVO extends User {
  private List<String> roles;
}
