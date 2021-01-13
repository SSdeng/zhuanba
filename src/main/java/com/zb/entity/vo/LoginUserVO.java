package com.zb.entity.vo;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.zb.entity.User;

import lombok.Data;

/**
 * 用于存储登录用户用户名和密码
 * 
 * @author dengzhijian
 * @version 1.0
 **/
@Data
public class LoginUserVO implements Serializable {
    private static final long serialVersionUID = 2121369736591683324L;
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户名 唯一，不可为空
     */
    private String username;
    /**
     * 密码 不可为空
     */
    private String password;
    /**
     * 角色 用于权限认证
     */
    private String role = "user";

    public static LoginUserVO asVO(User user) {
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtils.copyProperties(user, loginUserVO);
        return loginUserVO;
    }
}
