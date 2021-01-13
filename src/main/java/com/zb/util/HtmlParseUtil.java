package com.zb.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.zb.entity.Category;
import com.zb.entity.User;
import com.zb.service.CategoryService;
import com.zb.service.UserService;
import javassist.expr.NewArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zb.entity.Item;

import javax.annotation.Resource;

/**
 * 爬取京东数据
 * 
 * @author dengzhijian
 * @version 1.0
 **/
public class HtmlParseUtil {

    @Resource
    private static UserService userService;
    @Resource
    private static CategoryService categoryService;

    public static List<Item> getItemsByJD(String keyword, long userId, long categoryId) throws IOException {
        // 获取请求
        URL url = new URL("https://search.jd.com/Search?keyword=" + keyword + "&enc=utf-8");
        Document document = Jsoup.parse(url, 3000);
        Element goodsList = document.getElementById("J_goodsList");
        Elements elements = goodsList.getElementsByTag("li");
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Element element = elements.get(i);
            String img = element.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = element.getElementsByClass("p-price").eq(0).text();
            String title = element.getElementsByClass("p-name").eq(0).text();
            System.out.println("------------");
            System.out.println("img" + img);
            System.out.println("price" + price.substring(1, 6));
            System.out.println("title" + title);
            Item item = new Item();
            item.setItemName(title);
            item.setPrice(BigDecimal.valueOf(Double.parseDouble(price.substring(1, 6))));
            item.setImage(img);
            item.setCount(1);
            item.setLevel(10);
            item.setUser(userService.findById(userId));
            List<Category> categoryList = new ArrayList<>();
            categoryList.add(categoryService.findById(categoryId));
            item.setCategories(categoryList);
            items.add(item);
        }
        return items;
    }
}
