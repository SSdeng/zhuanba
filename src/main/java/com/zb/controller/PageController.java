package com.zb.controller;

import com.zb.entity.vo.CategoryVO;
import com.zb.service.CategoryService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PageController {

    @Resource
    private CategoryService categoryService;

    @RequestMapping("/login")
    public String getLogin() {
        return "login";
    }

    @RequestMapping("/register")
    public String getRegister() {
        return "register";
    }

    @RequestMapping("/release")
    public ModelAndView getRelease() {
        ModelAndView modelAndView = new ModelAndView("release");
        List<CategoryVO> categories = categoryService.getAllCategories();
        modelAndView.addObject("categories",categories);
        return modelAndView;
    }

    @RequestMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

    @RequestMapping("/root")
    public String getRoot() {
        return "root";
    }

    @RequestMapping("/wantsrelease")
    public String getWantsRelease() {
        return "wantsrelease";
    }

    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }



}
