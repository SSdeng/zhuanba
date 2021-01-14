package com.zb.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类VO
 * shenmanjie
 * 2021/1/14 11:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVO {
    /**
     * 分类ID
     */
    private Long id;

    /**
     * 分类名
     */
    private String name;
}
