package com.zb.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.*;

/**
 * 用户实体
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements UserDetails {
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

    /**
     * 获取角色权限
     *
     * @return 角色权限列表
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + getRole()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
