package com.zb.controller;

import com.zb.entity.Item;
import com.zb.entity.vo.CategoryVO;
import com.zb.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * CategoryController
 * shenmanjie
 * 2021/1/8 11:23
 */
@Controller
@RequestMapping("/api/category")
public class CategoryController {

    @Resource
    CategoryService categoryService;

    /**
     * 返回指定分类下所有的Item
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/find")
    public String getSpecificCategoryItems(@RequestParam("categoryId") Long categoryId,
                                           @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                           Model model) {

        //获取指定分类下的商品列表分页
        //PaginationSupport<Item> page = categoryService.getSpecificCategoryItems(categoryId,pageNo,pageSize);
        Page<Item> page = categoryService.getSpecificCategoryItemsByNamingParameters(categoryId,pageNo,pageSize);
        //List<Item> items = page.getContent();
        //获取所有分类
        List<CategoryVO> categories = categoryService.getAllCategories();


        model.addAttribute("items", page);
        model.addAttribute("categories", categories);

        return "index";
    }
}
