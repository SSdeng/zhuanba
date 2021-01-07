package com.zb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZhuanbaApplicationTests {

  @Autowired private UserMapper userMapper;
  @Autowired private RoleMapper roleMapper;

  @Test
  void contextLoads() {}
}
