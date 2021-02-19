package com.joezhou.springboot2security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author JoeZhou
 */
@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("admin")
    public String admin() {
        return "auth-success";
    }

    @PreAuthorize("hasRole('ROLE_COMM')")
    @RequestMapping("comm")
    public String comm() {
        return "auth-success";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COMM')")
    @RequestMapping("all")
    public String all() {
        return "auth-success";
    }

    @PreAuthorize("hasAuthority('select')")
    @RequestMapping("select")
    public String select() {
        return "auth-success";
    }

    @PreAuthorize("hasAnyAuthority('delete','update','insert')")
    @RequestMapping("dml")
    public String dml() {
        return "auth-success";
    }
}