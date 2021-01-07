package com.zb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zb.mapper.CategoryMapper;
import com.zb.mapper.ItemMapper;
import com.zb.mapper.UserMapper;
import com.zb.pojo.Category;

@SpringBootTest
class ZhuanbaApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void contextLoads() {
        // User user = userMapper.selectByPrimaryKey(1);
        // System.out.println(user);
        Category item = categoryMapper.selectByPrimaryKey(1);
        System.out.println(item);
    }
}
