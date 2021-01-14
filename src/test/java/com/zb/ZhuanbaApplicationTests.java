package com.zb;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.zb.service.CartService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.zb.elasticsearch.ItemEsRepository;
import com.zb.entity.Cart;
import com.zb.entity.Category;
import com.zb.entity.Item;
import com.zb.entity.User;
import com.zb.repository.ItemRepository;
import com.zb.service.CategoryService;
import com.zb.service.ItemService;
import com.zb.service.UserService;
import com.zb.util.HtmlParseUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ZhuanbaApplicationTests {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemEsRepository itemEsRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    User creatUser() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("zhangsan");
        return user;
    }

    @Test
    void addUser() {
        User user = creatUser();
        userService.insertSelective(user);
        System.out.println("userId: " + user.getId());
        Cart cart = user.getCart();
        System.out.println("cartId: " + cart.getId());
        System.out.println("cartUserId: " + cart.getUser().getId());
    }

    @Test
    @Transactional
    void addItem() {
        Item item = new Item();
        item.setItemName("ljc");
        item.setPrice(new BigDecimal(10.0));
        // User user = creatUser();
        // userService.insertSelective(user);
        User user = userService.findByUserName("zhangsan");
        item.setUser(user);
        itemService.insertSelective(item);
        System.out.println("\nitemInfo: " + item.toString() + "\n");
        System.out.println("\nitemUserInfo: " + item.getUser().toString() + "\n");
    }

    @Test
    @Transactional
    void contextLoads() {
        Page<Item> items = itemRepository.findItemsByCategories_id(1l, PageRequest.of(0, 1));
        List<Item> content = items.getContent();
        System.out.println("items: " + content.size());
        for (Item item : content) {
            System.out.println(item);
        }
    }

    @Test
    void removeCartTest() {
        long userId = 2;
        long itemId = 32;
        cartService.removeOrder(userId, itemId);
    }

    @Test
    void deleteUserTest() {
        userService.deleteUserById((long)6);
    }

    @Test
    void passwordTest(){
        User user = new User();
        user.setUsername("admin1");
        user.setPassword("admin1");
        String newPassword = new Md5Hash(user.getPassword(), user.getUsername(), 2).toString();
        user.setPassword(newPassword);
        log.info("New Password Is " + newPassword);
    }

    /**@Test
    @Transactional
    @Rollback(false)
    void paquItems() throws IOException {
        String key = "笔记本电脑";
        Long uId = (long)2;
        Long[] cIds = {(long)2};
        List<Item> items = HtmlParseUtil.getItemsByJD(key, uId, cIds);
        itemRepository.saveAll(items);
    }*/

    /*@Test
    @Transactional
    void test1() {
        List<Item> items = itemRepository.findAll();
        for (Item item : items) {
            List<Category> categories = item.getCategories();
            categories.get(0).toString();
            System.out.println(item);
        }
    }*/

    /*@Test
    @Transactional
    public void testGetSpecificCategoryItems() {
    
        PaginationSupport<Item> page = categoryService.getSpecificCategoryItems(1, 1, 2);
        List<Item> items = page.getItems();
        for (Item i : items) {
            System.out.println("商品：" + i);
        }
    }*/
    @Test
    @Transactional
    public void search() {
        Item item = itemRepository.findById(2L).get();
        itemEsRepository.save(item);
    }

    @Test
    @Transactional
    public void addData() {
        List<Item> item = itemRepository.findAll();
        itemEsRepository.saveAll(item);
    }
}
