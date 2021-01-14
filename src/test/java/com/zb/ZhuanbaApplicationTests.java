package com.zb;

import java.math.BigDecimal;
import java.io.IOException;
import java.util.List;

import com.zb.entity.*;
import com.zb.repository.CartRepository;
import com.zb.service.CartService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import com.zb.elasticsearch.ItemEsRepository;
import com.zb.repository.ItemRepository;
import com.zb.service.CategoryService;
import com.zb.util.HtmlParseUtil;

import javax.transaction.Transactional;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class ZhuanbaApplicationTests {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @Resource
    private CartService cartService;
    @Resource
    private CartRepository cartRepository;


    @Test
    @Transactional
    void cartTest() {
        Cart cart = cartService.findCartById((long)2);
        List<CartOrder> list = cart.getOrderList();
        System.out.println("firstTime\n\n");
        for (CartOrder cartOrder : list) {
            System.out.println(cartOrder.toString());
        }
        cartService.removeOrder((long)2, (long)1);
        cartRepository.save(cart);
        System.out.println("SecondTime\n\n");
        cart = cartService.findCartById((long)2);
        list = cart.getOrderList();
        for (CartOrder cartOrder : list) {
            System.out.println(cartOrder.toString());
        }
    }


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
        //User user = creatUser();
        //userService.insertSelective(user);
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
/*    @Test
    @Rollback(value = false)
    public void tes1() throws IOException {
        itemRepository.saveAll(HtmlParseUtil.getItemsByJD("图书"));
    }*/

    @Autowired
    private ItemEsRepository repository;
    @Test
    @Transactional
    public void saveAll(){
        List<Item> all = itemRepository.findAll();
        repository.saveAll(all);
    }
}
