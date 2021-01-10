package com.zb.entity;

import com.sun.istack.Nullable;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 求购信息
 *
 * @author lijiacheng
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "sys_wants")
@DynamicInsert // 动态插入
@DynamicUpdate // 动态更新
public class Wants extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 求购标题
     *  不可为空
     */
    @Column(nullable = false)
    private String title;
    /**
     * 求购描述
     *  默认为空串
     */
    private String description = "";
    /**
     * 求购图片地址
     */
    private String image;
    /**
     * 求购所属用户
     */
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
}
