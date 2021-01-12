package com.zb;

import com.zb.entity.Item;
import com.zb.repository.CategoryRepository;
import com.zb.repository.ItemRepository;
import com.zb.service.CategoryService;
import com.zb.util.PaginationSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class ZhuanbaApplicationTests {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @Test
    void contextLoads() {
        // User user = userMapper.selectByPrimaryKey(1);
        // System.out.println(user);
        //Category item = categoryRepository.selectByPrimaryKey(1);
        //System.out.println(item);
    }

    @Test
    @Transactional
    public void testGetSpecificCategoryItems(){

        PaginationSupport<Item> page = categoryService.getSpecificCategoryItems(1,1,2);
        List<Item> items = page.getItems();
        for(Item i : items){
            System.out.println("商品："+i);
        }
    }
}
