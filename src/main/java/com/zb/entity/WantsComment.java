package com.zb.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 求购评论
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
@Table(name = "sys_wcomment")
@DynamicInsert
@DynamicUpdate
public class WantsComment extends BaseComment implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 所属求购
     */
    @ManyToOne(targetEntity = Wants.class)
    @JoinColumn(name = "wants_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Wants wants;
    /**
     * 发表用户
     */
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

}
