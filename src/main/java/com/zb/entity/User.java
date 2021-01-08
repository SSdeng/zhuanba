package com.zb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.*;

/**
 * 用户实体
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = -2249088288605052627L;
    private Integer id;
    private String username;
    private String password;
    private String realName;
    private String phoneNumber;
    private String email;
    private Integer gender;
    private String academy;
    private String grade;
    private Date createTime;
    private Date updateTime;
    private String studentNumber;
    private Integer deleted;
    private String role;
    private List<Item> items;
}