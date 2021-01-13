package com.zb.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zb.entity.Category;
import com.zb.entity.Item;
import com.zb.service.CategoryService;
import com.zb.service.ItemService;

/**
 * @author lijiacheng
 * @version 1.0
 */
@Controller
public class HomeController {

    @Resource
    private ItemService itemService;
    @Resource
    private CategoryService categoryService;

    /**
     * 首页信息 包括所有商品和分类
     *
     * @param model
     * @param pageNo
     *            分页页码
     * @return 分页后信息
     */
    @GetMapping("/")
    public String allItems(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<Item> items = itemService.findAllByPage(pageNo, pageSize);
        model.addAttribute("items", items);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "index";
    }

}
