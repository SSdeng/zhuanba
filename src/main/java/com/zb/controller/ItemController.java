package com.zb.controller;

import java.io.IOException;

import javax.annotation.Resource;

import com.zb.entity.vo.ItemVO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.zb.entity.Item;
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

    /**
     * 发布商品处理
     *
     * @param item
     *            新的商品
     * @return 发布结果
     */
    @PostMapping("/release")
    @ResponseBody
    public Result releaseItem(@RequestBody ItemVO itemVO) {
        Item insert = itemService.insertSelective(itemVO);
        if (insert == null) {
            return Result.error(-1);
        }
        return Result.ok(insert.getId());
    }

    /**
     * 查看指定id商品详情
     *
     * @param itemId
     *            商品id
     * @return 查看结果
     */
    @GetMapping("/details")
    @ResponseBody
    public Result itemDetails(Model model, @RequestParam("itemId") int itemId) {
        Item item = itemService.findById(itemId);
        if (item == null) {
            return Result.error("Find no item");
        }
        model.addAttribute("item",item);
        model.addAttribute("categories",item.getCategories());
        model.addAttribute("comments",item.getItemComments());
        return Result.ok(model);
    }

    /**
     * 分页查找所有商品
     *
     * @param pageNo
     *            起始页码
     * @param pageSize
     *            分页大小
     * @return 分页后所有商品
     */
    @GetMapping("/all")
    public Result itemAll(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        //TODO 转移至home“/”
        Page<Item> items = itemService.findAllByPage(pageNo, pageSize);
        return Result.ok("pageAll", items);
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
     * @param itemId
     * @param image
     */
    @PostMapping("/upload")
    @ResponseBody
    public Result uploadPicture(Model model, @RequestParam("itemId") int itemId, @RequestParam("image") MultipartFile image) throws IOException {

        // 使用图片上传工具类，接受文件后，返回文件的新名称
        String itemPictureName = FileUtil.uploadFile(image);

        Item item = itemService.findById(itemId);

        item.setImage(itemPictureName);

        item = itemService.updateItemInfo(item);

        model.addAttribute("item",item);

        return Result.ok();

    }

    /**
     * 进入购买商品
     *
     * @param model
     * @param itemId 商品id
     * @return 商品id，数量（1），价格
     */
    @PostMapping("/buy")
    @ResponseBody
    public Result bugItem(Model model, @RequestParam("itemId") Long itemId){
        Item item = itemService.findById(itemId);
        if (item == null) {
            return Result.error();
        }
        model.addAttribute("itemId",item.getId());
        model.addAttribute("number",1);
        model.addAttribute("cost",item.getPrice());
        return Result.ok();
    }
}
