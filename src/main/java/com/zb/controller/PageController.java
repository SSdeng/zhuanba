package com.zb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/index")
    public String getIndex(){
        return "index";
    }

    @RequestMapping("/item")
    public String getItem(){
        return "item";
    }

    @RequestMapping("/login")
    public String getLogin(){
        return "login";
    }

    @RequestMapping("/register")
    public String getRegister(){
        return "register";
    }

    @RequestMapping("/release")
    public String getRelease(){
        return "release";
    }
}
