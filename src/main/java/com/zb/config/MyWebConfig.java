package com.zb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 静态资源配置类
 *
 * @author lijiacheng
 * @version 1.0
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    // 设置静态文件的目录
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String resourceLocation = "file:" + System.getProperty("user.dir") + "/src/main/resources/static/images/";
        registry.addResourceHandler("/images/**").addResourceLocations(resourceLocation);
    }
}