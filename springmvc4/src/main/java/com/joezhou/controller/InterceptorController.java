package com.joezhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JoeZhou
 */
@Controller
@RequestMapping("/api/interceptor")
public class InterceptorController {

    @ResponseBody
    @RequestMapping("test")
    public String test() {
        System.out.println("test()...");
        return "success";
    }
}