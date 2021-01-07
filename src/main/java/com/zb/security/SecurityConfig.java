package com.zb.security;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zb.vo.Result;

/** 安全认证设置 登录设置 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailService myUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/register").permitAll().anyRequest().authenticated().and()
            .formLogin().loginProcessingUrl("/doLogin")
            // 登录成功后返回给前端的json数据
            .successHandler((req, resp, authentication) -> {
                Result ok = Result.ok("登录成功！", authentication.getPrincipal());
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write(new ObjectMapper().writeValueAsString(ok));
                out.flush();
                out.close();
            })
            // 登录失败后返回给前端的json数据
            .failureHandler((req, resp, e) -> {
                Result error = Result.error("登录失败");
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write(new ObjectMapper().writeValueAsString(error));
                out.flush();
                out.close();
            }).loginPage("/login").permitAll().and().logout().logoutUrl("/logout")
            // 注销成功后返回给前端的json数据
            .logoutSuccessHandler((req, resp, authentication) -> {
                Result ok = Result.ok("注销成功！");
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write(new ObjectMapper().writeValueAsString(ok));
                out.flush();
                out.close();
            }).permitAll().and()
            // 关闭csrf
            .csrf().disable()
            // 无权限访问返回给前端的json数据
            .exceptionHandling().accessDeniedHandler((req, resp, e) -> {
                Result error = Result.error("权限不足，访问失败");
                resp.setStatus(403);
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write(new ObjectMapper().writeValueAsString(error));
                out.flush();
                out.close();
            });
        http.addFilterAt(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 自定义验证用户密码过滤器
     *
     * @return
     * @throws Exception
     */
    @Bean
    MyAuthenticationFilter myAuthenticationFilter() throws Exception {
        MyAuthenticationFilter filter = new MyAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    /**
     * 设置加密方式
     *
     * @return BCrypt密码加密
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 用户认证
     * 
     * @param auth
     *            登陆管理器
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**", "/fonts/**", "/img/**", "/jquery/**", "/js/**", "/layer/**");
    }
}
