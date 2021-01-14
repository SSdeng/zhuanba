package com.zb.config;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zb.shiro.MySessionManager;
import com.zb.shiro.MyShiroRealm;
import com.zb.shiro.RoleOrFilter;

import lombok.Data;

/**
 * shiro配置
 * 
 * @author dengzhijian
 * @version 1.0
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "spring.redis")
public class ShiroConfig {

    private String host;
    private int port;
    private Duration timeout;

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     *
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 添加自定义过滤器
        LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
        filtersMap.put("roleOrFilter", new RoleOrFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        // 过滤器链定义映射
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 首页放行
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/search", "anon");
        filterChainDefinitionMap.put("/api/category/find", "anon");
        // 注册、登录放行
        filterChainDefinitionMap.put("/api/user/login", "anon");
        filterChainDefinitionMap.put("/api/user/register", "anon");
        filterChainDefinitionMap.put("/register", "anon");
        // 静态资源放行
        filterChainDefinitionMap.put("/bootstrap/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/jquery/**", "anon");
        filterChainDefinitionMap.put("/layer/**", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        // druid放行
        filterChainDefinitionMap.put("/druid/**", "anon");
        // 所有url都必须认证通过才可以访问
        filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/api/user/**", "roles[user]");
        filterChainDefinitionMap.put("/api/wants/**", "roles[user]");
        filterChainDefinitionMap.put("/api/wants/all", "anon");
        filterChainDefinitionMap.put("/api/order/**", "roles[user]");
        filterChainDefinitionMap.put("/api/item/**", "roles[user]");
        filterChainDefinitionMap.put("/api/comment/**", "roles[user]");
        filterChainDefinitionMap.put("/api/collection/**", "roles[user]");
        filterChainDefinitionMap.put("/api/admin/**", "roleOrFilter[root,admin]");
        filterChainDefinitionMap.put("/api/root/**", "roles[root]");
        // 设置未授权路由，之后再返回json数据给前端
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 未授权界面, 对应LoginController中 unauthorized 请求
        shiroFilterFactoryBean.setUnauthorizedUrl("/api/user/unauthorized");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器（由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
     *
     * @return HashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * 将自己的验证方式加入容器
     *
     * @return MyShiroRealm
     */
    @Bean
    public MyShiroRealm myShiroRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myShiroRealm;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis, 使用的是shiro-redis开源插件
     *
     * @return RedisSessionDAO
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(SessionIdGenerator sessionIdGenerator) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setSessionIdGenerator(sessionIdGenerator);
        redisSessionDAO.setExpire(1800);
        return redisSessionDAO;
    }

    /**
     * Session ID 生成器 <br/>
     *
     * @return JavaUuidSessionIdGenerator
     */
    @Bean
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    /**
     * 自定义sessionManager
     *
     * @return SessionManager
     */
    @Bean
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        MySessionManager mySessionManager = new MySessionManager();
        mySessionManager.setSessionDAO(redisSessionDAO);
        mySessionManager.setSessionIdUrlRewritingEnabled(false);
        return mySessionManager;
    }

    /**
     * 配置shiro redisManager, 使用的是shiro-redis开源插件 <br/>
     *
     * @return RedisManager
     */
    private RedisManager redisManager() {
        return new RedisManager();
    }

    /**
     * cacheManager 缓存 redis实现, 使用的是shiro-redis开源插件 <br/>
     *
     * @return RedisCacheManager
     */
    @Bean
    public RedisCacheManager shiroCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        // 必须要设置主键名称，shiro-redis 插件用过这个缓存用户信息
        redisCacheManager.setPrincipalIdFieldName("id");
        return redisCacheManager;
    }

    /**
     * 权限管理，配置主要是Realm的管理认证 create time: 2019/7/1 10:09
     *
     * @return SecurityManager
     */
    @Bean
    public SecurityManager securityManager(MyShiroRealm myShiroRealm, SessionManager sessionManager,
        CacheManager cacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm);
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager);
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }

    /*
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
            new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public SimpleCookie RememberMeCookie() {
        // cookie的name,对应的默认是 JSESSIONID
        SimpleCookie cookie = new SimpleCookie("SHARE_JSESSIONID");
        cookie.setHttpOnly(true);
        // path为 / 用于多个系统共享 JSESSIONID
        cookie.setPath("/");
        return cookie;
    }
}
