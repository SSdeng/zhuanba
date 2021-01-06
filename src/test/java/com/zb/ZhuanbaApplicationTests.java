package com.zb;

import com.zb.mapper.RoleMapper;
import com.zb.mapper.UserMapper;
import com.zb.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ZhuanbaApplicationTests {

  @Autowired private UserMapper userMapper;
  @Autowired private RoleMapper roleMapper;

  @Test
  void contextLoads() {
    List<User> users = userMapper.selectList(null);
    for (User user : users) {
      System.out.println(user);
    }
  }
}
