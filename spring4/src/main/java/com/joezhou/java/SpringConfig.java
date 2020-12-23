package com.joezhou.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author JoeZhou
 */
@Configuration
@ComponentScan("com.joezhou.java")
public class SpringConfig {

    /**手动装配deptDao的<bean>
     *
     * 相当于xml中的<bean id="accountDao" class="com.joezhou.java.AccountDao"/>`。
     */
    @Bean
    public AccountDao accountDao() {
        return new AccountDao();
    }
}