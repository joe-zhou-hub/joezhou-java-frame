package com.joezhou.springboot2.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author JoeZhou
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private UserService userService;

    @Autowired
    public InterceptorConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        registry.addInterceptor(new AuthInterceptor(userService))
                .addPathPatterns("/**");
    }
}