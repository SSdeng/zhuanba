package com.zb.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zb.elasticsearch.ItemEsRepository;
import com.zb.entity.Item;
import com.zb.entity.vo.CategoryVO;
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
    @Resource
    private ItemEsRepository repository;

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
        // 从索引库调用所有商品
        Page<Item> items = repository.findAll(PageRequest.of(pageNo - 1, pageSize));
        model.addAttribute("items", items);
        List<CategoryVO> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("b", false);
        return "index";
    }

}
