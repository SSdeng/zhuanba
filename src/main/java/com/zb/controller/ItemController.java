package com.zb.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.zb.entity.vo.ItemVO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.zb.entity.Item;
import com.zb.service.ItemService;
import com.zb.util.FileUtil;
import com.zb.util.Result;
import org.springframework.web.servlet.ModelAndView;

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
     * @param itemId
     *            商品id
     * @return 商品信息
     */
    @GetMapping("/details")
    public ModelAndView itemDetails(@RequestParam("itemId") long itemId) {
        ModelAndView modelAndView = new ModelAndView("item");
        Item item = itemService.findById(itemId);
        modelAndView.addObject("item", item);
        modelAndView.addObject("comments", item.getItemComments());
        modelAndView.addObject("categories", item.getCategories());
        return modelAndView;
    }

    /**
     * 根据输入信息搜索商品
     *
     * @param searchInfo
     *            搜索信息
     * @param pageNo
     *            起始页码
     * @param pageSize
     *            分页大小
     * @return 分页后搜索结果
     */
    @GetMapping("/search")
    @ResponseBody
    public Result itemSearch(@RequestParam("searchInfo") String searchInfo,
        @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        //TODO
        Page<Item> items = itemService.searchByPage(searchInfo, pageNo, pageSize);
        return Result.ok("searchResultPage", items);
    }

    /**
     * 接受文件图片
     *
     * @param itemId 商品id
     * @param image 图片对象
     * @return 接收后商品
     */
    @PostMapping("/upload")
    public ModelAndView uploadPicture(@RequestParam("itemId") long itemId, @RequestParam("image") MultipartFile image, HttpSession session) throws IOException {
        ModelAndView modelAndView = new ModelAndView("item");
        // 使用图片上传工具类，接受文件后，返回文件的新名称
        String itemPictureName = FileUtil.uploadFile(image, session);
        Item item = itemService.setImageById(itemId, itemPictureName);
        modelAndView.addObject("item",item);
        return modelAndView;
    }

    /**
     * 进入购买商品
     *
     * @param itemId 商品id
     * @return 商品id，数量（1），价格
     */
    @PostMapping("/buy")
    public ModelAndView bugItem(@RequestParam("itemId") long itemId){
        ModelAndView modelAndView = new ModelAndView("buy");
        Item item = itemService.findById(itemId);
        modelAndView.addObject("itemId", item.getId());
        modelAndView.addObject("cost",item.getPrice());
        modelAndView.addObject("number", 1);
        return modelAndView;
    }
}
