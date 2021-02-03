package com.joezhou.springboot2.servlet.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JoeZhou
 */
@Configuration
public class BeanFilterConfig {

    @SuppressWarnings("all")
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filters = new FilterRegistrationBean();
        filters.setFilter(new BeanFilter());
        // 不支持部分模糊，如 `/test` 或 `/*test`
        filters.addUrlPatterns("/api/servlet/*");
        return filters;
    }
}
