package com.joezhou.springboot2security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author JoeZhou
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

    @RequestMapping("login-page-routing")
    public String loginPageRouting() {
        System.out.println("loginPageRouting()...");
        return "login";
    }
    
    @RequestMapping("login-success")
    public String loginSuccess() {
        System.out.println("loginSuccess()...");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("principal：" + authentication.getPrincipal());
        System.out.println("authorities：" + authentication.getAuthorities());
        System.out.println("username：" + authentication.getName());
        return "main";
    }
}