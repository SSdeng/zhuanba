package com.zb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zb.entity.Category;
import com.zb.repository.CategoryRepository;
import com.zb.repository.ItemRepository;

@SpringBootTest
class ZhuanbaApplicationTests {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
        // User user = userMapper.selectByPrimaryKey(1);
        // System.out.println(user);
        //Category item = categoryRepository.selectByPrimaryKey(1);
        //System.out.println(item);
    }
}
