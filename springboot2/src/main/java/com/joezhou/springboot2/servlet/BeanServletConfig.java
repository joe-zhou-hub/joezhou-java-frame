package com.joezhou.springboot2.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JoeZhou
 */
@Configuration
public class BeanServletConfig {

    @SuppressWarnings("all")
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new BeanServlet(), "/api/servlet/bean");
    }
}
