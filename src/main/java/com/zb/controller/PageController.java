package com.zb.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zb.entity.vo.CategoryVO;
import com.zb.service.CategoryService;

/**
 * 页面控制器，用来跳转基本页面
 * 
 * @author dengzhijian
 */
@Controller
public class PageController {

    @Resource
    private CategoryService categoryService;

    /**
     * 跳转到登录页面
     * 
     * @return login
     */
    @RequestMapping("/login")
    public String getLogin() {
        return "login";
    }

    /**
     * 跳转到注册页面
     * 
     * @return 注册页面
     */
    @RequestMapping("/register")
    public String getRegister() {
        return "register";
    }

    /**
     * 获取所有分类信息
     * 
     * @return 所有分类
     */
    @RequestMapping("/release")
    public ModelAndView getRelease() {
        ModelAndView modelAndView = new ModelAndView("release");
        List<CategoryVO> categories = categoryService.getAllCategories();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    /**
     * 跳转到管理员页面
     * 
     * @return admin
     */
    @RequestMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

    /**
     * 导航到求购页面
     * 
     * @return 求购页面
     */
    @RequestMapping("/wantsrelease")
    public String getWantsRelease() {
        return "wantsrelease";
    }

    /**
     * 用于登出
     * 
     * @return 返回登录页面
     */
    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    /**
     * 返回禁止访问页面
     *
     * @return 页面
     */
    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
}
