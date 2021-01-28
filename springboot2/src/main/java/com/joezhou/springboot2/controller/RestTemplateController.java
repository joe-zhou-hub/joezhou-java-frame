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
        System.out.println("getMapping()..." + name);
        return "data: " + name;
    }

    @ResponseBody
    @PostMapping("post-mapping")
    public String postMapping(String name) {
        System.out.println("postMapping()..." + name);
        return "data: " + name;
    }

    @RequestMapping("redirect")
    public String redirect(String name) {
        System.out.println("redirect()..." + name);
        // `/` 不能省略
        return "redirect:/success.html";
    }

}
