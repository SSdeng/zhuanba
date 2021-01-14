package com.zb.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zb.entity.Item;
import com.zb.entity.vo.CategoryVO;
import com.zb.service.CategoryService;
import com.zb.service.ItemService;

/**
 * 主页控制器
 * 
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
     *            模型
     * @param pageNo
     *            分页页码
     * @return 分页后信息
     */
    @GetMapping("/")
    public String allItems(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        // Page<Item> items = itemService.findAllPage(pageNo, pageSize);
        // 首页只展示已经审核过的商品
        Page<Item> items = itemService.findAllPageByStatus(1, pageNo, pageSize);
        model.addAttribute("items", items);
        List<CategoryVO> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("b", 1);
        return "index";
    }

}
