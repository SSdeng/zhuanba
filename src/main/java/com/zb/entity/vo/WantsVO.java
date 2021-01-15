package com.zb.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 求购VO
 * 
 * @author YeFeng
 * @version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WantsVO {

    /**
     * wantsId
     */
    private Long id;
    /**
     * 求购标题 不可为空
     */
    private String title;
    /**
     * 求购描述 默认为空串
     */
    private String description = "";
    /**
     * 求购图片地址
     */
    private String image = "/images/logo2.png";

}
