package com.joezhou.springboot2security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author JoeZhou
 */
/*@Configuration
@EnableWebSecurity*/
public class LoginConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.formLogin()
                .loginPage("/login")
                .successForwardUrl("/login-success")
                .permitAll();

        http.authorizeRequests()
                .antMatchers("/", "/index.html").permitAll()
                .antMatchers("/css/**", "/js/**", "/font/**").permitAll().
                anyRequest().fullyAuthenticated();



    }
}