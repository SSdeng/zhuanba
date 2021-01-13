package com.zb.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    // @RequestMapping("/")
    // public String getIndex1(){
    // return "index";
    // }

    @RequestMapping("/index")
    public String getIndex() {
        return "index";
    }

    // @RequestMapping("/item")
    // public String getItem(){
    // return "item";
    // }

    @RequestMapping("/login")
    public String getLogin() {
        return "login";
    }

    @RequestMapping("/register")
    public String getRegister() {
        return "register";
    }

    @RequestMapping("/release")
    public String getRelease() {
        return "release";
    }

    @RequestMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
