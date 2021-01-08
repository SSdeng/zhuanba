package com.zb.controller;

import com.zb.entity.Category;
import com.zb.service.CategoryService;
import com.zb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * CategoryController
 * shenmanjie
 * 2021/1/8 11:23
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    CategoryService categoryService ;

    /**
     * 构造器依赖注入
     *
     * @param categoryService 商品服务
     */
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get")
    public Result getAllCategory(){

        List<Category> list = categoryService.selectAll();

        HashMap<Integer,String> map = new HashMap<Integer,String>();

        for(Category category : list){
            map.put(category.getId(), category.getName());
        }

        return Result.ok("返回部门信息",map);
    }

    @GetMapping("/find")
    public Result getSpecificCategoryItem(@RequestParam("categoryId") int categoryId){

        return null;
    }
}
