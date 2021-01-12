package com.zb.controller;

import com.zb.entity.Item;
import com.zb.entity.Wants;
import com.zb.service.UserService;
import com.zb.service.WantsService;
import com.zb.util.FileUtil;
import com.zb.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

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

    /**
     * 发布新求购
     *
     * @param userId 发布用户id
     * @param wants 求购信息
     * @return 求购id
     */
    @PostMapping("/release")
    @ResponseBody
    public Result releaseWants(@RequestParam("userId") Long userId, @RequestBody Wants wants){
        wants.setUser(userService.findById(userId));
        wants = wantsService.insertSelective(wants);
        if(wants == null){
            return Result.error("Release Failed");
        }
        return Result.ok(wants.getId());
    }

    /**
     * 上传求购图片
     *
     * @param wantsId 求购id
     * @param file 上传图片
     * @return 上传结果
     */
    @PostMapping("/upload")
    @ResponseBody
    public Result uploadPicture(Model model, @RequestParam("wantsId") Long wantsId, @RequestParam("file") MultipartFile file) throws IOException {

        String fileName = FileUtil.uploadFile(file);
        Wants wants = wantsService.getById(wantsId);
        wants.setImage(fileName);
        wants = wantsService.updateWants(wants);
        model.addAttribute("wants",wants);
        return Result.ok();
    }
}
