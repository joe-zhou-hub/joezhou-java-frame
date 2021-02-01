package com.joezhou.springboot2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author JoeZhou
 */
@Controller
@RequestMapping("api/thymeleaf")
public class ThymeleafController {

    @RequestMapping("test")
    public String test(Map<String, Object> map, String name) {
        map.put("msg", name);
        // 搭配前缀后缀，组成/templates/html/main.html
        return "main2";
    }
}