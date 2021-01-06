package com.zb.pojo.VO;

import com.zb.pojo.Role;
import com.zb.pojo.User;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class RoleVO extends Role {
  private List<User> users;
}
