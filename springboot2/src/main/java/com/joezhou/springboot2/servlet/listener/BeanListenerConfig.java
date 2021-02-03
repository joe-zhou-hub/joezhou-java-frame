package com.joezhou.springboot2.servlet.listener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JoeZhou
 */
@Configuration
public class BeanListenerConfig {


    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        //多个监听器，则需要配置多个@Bean方法。
        return new ServletListenerRegistrationBean<>(new BeanListener());
    }
}
