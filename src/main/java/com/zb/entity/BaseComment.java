package com.zb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 评论公共属性字段
 *
 * @author lijiacheng
 * @version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseComment extends BaseEntity{

    /**
     * 评论内容
     */
    @Column(nullable = false)
    private String content;

}
