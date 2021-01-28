package com.joezhou.springboot2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JoeZhou
 */
@Controller
@RequestMapping("api/rest-template")
public class RestTemplateController {

    @ResponseBody
    @GetMapping("get-mapping")
    public String getMapping(String name) {
        return "getMapping()..." + name;
    }

    @ResponseBody
    @PostMapping("post-mapping")
    public String postMapping(String name) {
        return "postMapping()..." + name;
    }

    @RequestMapping("request-mapping")
    public String requestMapping() {
        System.out.println("requestMapping()...");
        return "redirect:/success.html";
    }
}
