package com.zb.controller;

import com.zb.entity.Item;
import com.zb.entity.Wants;
import com.zb.entity.vo.CategoryVO;
import com.zb.service.CategoryService;
import com.zb.service.UserService;
import com.zb.service.WantsService;
import com.zb.util.FileUtil;
import com.zb.util.Result;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 求购项控制器
 *
 * @author lijiacheng
 * @version 1.0
 */
@Controller
@RequestMapping("/api/wants")
public class WantsController {

    @Resource
    private WantsService wantsService;
    @Resource
    private UserService userService;
    @Resource
    private CategoryService categoryService;

    /**
     * 返回求购发布页
     *
     * @return 求购发布页
     */
    @GetMapping("release")
    public String editWants() {
        return "wantsrelease";
    }

    /**
     * 发布新求购
     *
     * @param userId 发布用户id
     * @param wants  求购信息
     * @return 求购id
     */
    @PostMapping("/release")
    @ResponseBody
    public Result releaseWants(@RequestParam("userId") Long userId, @RequestBody Wants wants) {
        wants.setUser(userService.findById(userId));
        wants = wantsService.insertSelective(wants);
        Map<String, Object> data = new HashMap<>();
        data.put("wantsId", wants.getId());
        return Result.ok("发布成功", data);
    }

    /**
     * 查看指定id求购详情
     *
     * @param wantsId 求购id
     * @return 求购信息
     */
    @GetMapping("/details")
    public ModelAndView wantsDetails(@RequestParam("wantsId") long wantsId) {
        ModelAndView modelAndView = new ModelAndView("wants");
        Wants wants = wantsService.findById(wantsId);
        modelAndView.addObject("wants", wants);
        modelAndView.addObject("comments", wants.getComments());
        return modelAndView;
    }

    /**
     * 删除求购
     *
     * @param wantsId 求购id
     * @return 删除结果
     */
    @GetMapping("/remove")
    public String removeWants(@RequestParam("userId") long userId, @RequestParam("wantsId") long wantsId) {
        wantsService.deleteById(wantsId);
        return "redirect:/api/user/info?userId" + userId;
    }

    /**
     * 上传求购图片
     *
     * @param wantsId 求购id
     * @param file    上传图片
     * @return 求购信息
     */
    @PostMapping("/upload")
    public ModelAndView uploadPicture(@RequestParam("wantsId") long wantsId, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        ModelAndView modelAndView = new ModelAndView("wants");
        String fileName = FileUtil.uploadFile(file, session);
        Wants wants = wantsService.setImageById(wantsId, fileName);
        modelAndView.addObject("wants", wants);
        return modelAndView;
    }

    /**
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/all")
    public String allWants(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<Wants> wants = wantsService.getAllByPage(pageNo, pageSize);
        List<CategoryVO> categories = categoryService.getAllCategories();
        model.addAttribute("items", wants);
        System.out.println(wants.getSize());
        for(Wants w : wants){
            System.out.println(w.getDescription());
        }
        model.addAttribute("categories", categories);
        model.addAttribute("b",3);
        return "index";
    }
}
