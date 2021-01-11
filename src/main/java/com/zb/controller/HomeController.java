package com.zb.controller;

import com.zb.entity.Category;
import com.zb.entity.Item;
import com.zb.repository.ItemRepository;
import com.zb.service.CategoryService;
import com.zb.service.ItemService;
import com.zb.util.Result;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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
     * 首页信息
     *  包括所有商品和分类
     *
     * @param model
     * @param pageNo 分页页码
     * @return 分页后信息
     */
    @GetMapping("/")
    @ResponseBody
    public Result allItems(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Page<Item> items = itemService.findAllByPage(pageNo, pageSize);
        model.addAttribute("items",items);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories",categories);
        return Result.ok();
    }
}
