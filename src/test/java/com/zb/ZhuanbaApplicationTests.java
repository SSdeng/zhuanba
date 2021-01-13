package com.zb;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.zb.entity.Category;
import com.zb.entity.Item;
import com.zb.repository.CategoryRepository;
import com.zb.repository.ItemRepository;
import com.zb.service.CategoryService;
import com.zb.util.PaginationSupport;

@SpringBootTest
class ZhuanbaApplicationTests {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @Test
    @Transactional
    void contextLoads() {
        Page<Item> items = itemRepository.findByCategories_id(1l, PageRequest.of(0, 1));
        List<Item> content = items.getContent();
        for (Item item : content) {
            System.out.println(item);
        }
    }

    @Test
    @Transactional
    void test1() {
        List<Item> items = itemRepository.findItemsBy();
        for (Item item : items) {
            List<Category> categories = item.getCategories();
            categories.get(0).toString();
            System.out.println(item);
        }
    }

    @Test
    @Transactional
    public void testGetSpecificCategoryItems() {

        PaginationSupport<Item> page = categoryService.getSpecificCategoryItems(1, 1, 2);
        List<Item> items = page.getItems();
        for (Item i : items) {
            System.out.println("商品：" + i);
        }
    }
}
