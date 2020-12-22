package com.joezhou.config;

import com.joezhou.dao.AccountDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author JoeZhou
 */
@Configuration
@ComponentScan(basePackages = "com.joezhou.service")
public class SpringConfig {

    /**手动装配deptDao的<bean>
     *
     * `@Bean` 相当于xml中的`<bean>`，下面的方法相当于：
     `<bean id="deptDao" class="com.joe.dao.DeptDao"/>`。
     */
    @Bean
    public AccountDao accountDao() {
        return new AccountDao();
    }
}