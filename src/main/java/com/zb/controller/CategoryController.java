package com.zb.controller;

import com.zb.entity.Category;
import com.zb.entity.Item;
import com.zb.service.CategoryService;
import com.zb.util.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * CategoryController
 * shenmanjie
 * 2021/1/8 11:23
 */
@Controller
@RequestMapping("/api/category")
public class CategoryController {

    CategoryService categoryService;

    /**
     * 构造器依赖注入
     *
     * @param categoryService 商品服务
     */
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    /**
     * 返回指定分类下所有的Item
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/find")
    public String getSpecificCategoryItems(@RequestParam("categoryId") int categoryId,
                                           @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                           Model model) {

        //获取指定分类下的商品列表分页
        PaginationSupport<Item> page = categoryService.getSpecificCategoryItems(categoryId,pageNo,pageSize);
        //List<Item> items = page.getItems();
        //获取所有分类
        List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("page", page);
        model.addAttribute("categories", categories);

        return "index";
    }
}
