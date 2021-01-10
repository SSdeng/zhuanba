package com.zb.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户地址
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
@Table(name = "sys_address")
@DynamicInsert
@DynamicUpdate
public class Address extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 地址详情
     *  不可为空
     */
    @Column(nullable = false)
    private String detail;
    /**
     * 所属用户
     */
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
}
