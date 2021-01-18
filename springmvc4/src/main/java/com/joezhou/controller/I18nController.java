package com.joezhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author JoeZhou
 */
@Controller
@RequestMapping("/api/i18n")
public class I18nController {

    @RequestMapping("test")
    public String test() {
        System.out.println("test()...");
        return "forward:/view/i18n.jsp";
    }
}