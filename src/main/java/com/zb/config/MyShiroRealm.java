package com.zb.config;

import java.util.Objects;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.zb.pojo.User;
import com.zb.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义 shiroRealm, 主要是重写其认证、授权
 * 
 * @author dengzhijian
 * @version 1.0
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 授权
     *
     * @return 权限信息，包括角色以及权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.warn("开始执行授权操作.......");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 如果身份认证的时候没有传入User对象，这里只能取到userName
        // 也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
        User user = (User)principals.getPrimaryPrincipal();
        authorizationInfo.addRole(user.getRole());
        return authorizationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确
     *
     * @return 身份验证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("开始进行身份认证......");

        // 获取用户的输入的账号.
        String userName = (String)token.getPrincipal();

        // 通过username从数据库中查找 User对象.
        User user = userService.findByUserName(userName);
        if (Objects.isNull(user)) {
            return null;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(userName), getName());
    }

}
