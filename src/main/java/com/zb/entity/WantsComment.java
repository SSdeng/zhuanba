package com.zb.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * 求购评论
 *
 * @author lijiacheng
 * @version 1.0
 */
public class WantsComment extends BaseComment implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 发表用户
     */
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

}
