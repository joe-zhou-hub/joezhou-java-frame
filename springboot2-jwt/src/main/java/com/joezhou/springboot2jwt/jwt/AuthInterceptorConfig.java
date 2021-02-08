package com.joezhou.springboot2jwt.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author JoeZhou
 */
@Configuration
public class AuthInterceptorConfig implements WebMvcConfigurer {

    private UserService userService;

    @Autowired
    public AuthInterceptorConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(userService))
                .addPathPatterns("/**");
    }
}