package com.zb.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zb.entity.Item;
import com.zb.entity.vo.CategoryVO;
import com.zb.entity.vo.ItemVO;
import com.zb.service.CartService;
import com.zb.service.CategoryService;
import com.zb.service.CollectionService;
import com.zb.service.ItemService;
import com.zb.util.FileUtil;
import com.zb.util.Result;

/**
 * 商品控制器
 *
 * @author lijiacheng
 * @version 1.0
 */
@Controller
@RequestMapping("/api/item")
public class ItemController {

    @Resource
    private ItemService itemService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private CartService cartService;
    @Resource
    private CollectionService collectionService;

    /**
     * 发布商品处理
     *
     * @param itemVO
     *            新的商品
     * @return 发布结果
     */
    @PostMapping("/release")
    @ResponseBody
    public Result releaseItem(@RequestBody ItemVO itemVO) {
        Item insert = itemService.insertSelective(itemVO);
        Map<String, Object> data = new HashMap<>();
        data.put("itemId", insert.getId());
        return Result.ok("发布成功", data);
    }

    /**
     * 查看指定id商品详情
     *
     * @param model
     *            模型
     * @param itemId
     *            商品id
     * @return 视图
     */
    @GetMapping("/details")
    public String itemDetails(Model model, @RequestParam("userId") long userId, @RequestParam("itemId") long itemId) {
        Item item = itemService.findById(itemId);
        if (!item.getStatus().equals(1)) {
            return "itemNotAvailable";
        }
        List<CategoryVO> categories = categoryService.getAllCategories();
        model.addAttribute("item", item);
        model.addAttribute("comments", item.getItemComments());
        model.addAttribute("categories", categories);
        model.addAttribute("inCart", cartService.hasItem(userId, itemId));
        model.addAttribute("inCollection", collectionService.hasItem(userId, itemId));
        return "item";
    }

    /**
     * 接受文件图片
     *
     * @param itemId
     *            商品id
     * @param image
     *            图片对象
     * @return 接收后商品
     */
    @PostMapping("/upload")
    public ModelAndView uploadPicture(@RequestParam("itemId") long itemId, @RequestParam("image") MultipartFile image)
        throws IOException {
        ModelAndView modelAndView = new ModelAndView("item");
        // 使用图片上传工具类，接受文件后，返回文件的新名称
        String itemPictureName = FileUtil.uploadFile(image);
        Item item = itemService.setImageById(itemId, itemPictureName);
        modelAndView.addObject("item", item);
        return modelAndView;
    }


    /**
     * 下架指定商品
     *
     * @param itemId
     *            商品id
     * @return Result
     */
    @PostMapping("/remove")
    @ResponseBody
    public Result deleteItem(@RequestParam("userId") long userId, @RequestParam("itemId") long itemId) {
        itemService.deleteById(itemId);
        return Result.ok();
    }
}
