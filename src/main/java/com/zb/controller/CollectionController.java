package com.zb.controller;


import com.zb.entity.User;
import com.zb.service.CollectionService;
import com.zb.service.UserService;
import com.zb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/collection")
public class CollectionController {
    CollectionService collectionService;
    UserService userService;

    /**
     * 构造器依赖注入
     *
     * @param collectionService 收藏服务
     */
    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService=collectionService;
    }

    @PostMapping("/add")
    public Result AddItem(@RequestParam("userId")Long  userId, @RequestParam("itemId") Long itemId){
        return Result.ok(collectionService.addItem(userId,itemId));

    }
    @PostMapping("/remove")
    public String RemoveItem(@RequestParam("collectionId")Long collectionId
            , @RequestParam("itemId") Long itemId){
        User user = collectionService.findById(collectionId).getUser();
        collectionService.removeItem(collectionId,itemId);
        return "redirect:/api/user/info?userId"+user.getId();
    }

}
