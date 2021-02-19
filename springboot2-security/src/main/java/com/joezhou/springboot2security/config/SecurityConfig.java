package com.joezhou.springboot2security.config;

import com.joezhou.springboot2security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author JoeZhou
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 后面UserDetailsServiceImpl中还要使用到配置类中的bean，
     * 这里不建议使用构造器方式注入UserDetailsServiceImpl，以免形成环注，导致服务器启动失败。
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsServiceImpl);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/index.html", "/ad.html").permitAll()
                .anyRequest().authenticated();

        http.csrf().disable();

        http.formLogin()
                .loginPage("/api/user/login-routing")
                .loginProcessingUrl("/login")
                .successForwardUrl("/api/user/main-routing")
                .permitAll();
    }
}