package com.joezhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author JoeZhou
 */
@RequestMapping("/api/request-mapping")
@Controller
public class RequestMappingController {

    @RequestMapping({"test1", "test2", "test3"})
    public String test() {
        System.out.println("test()...");
        return "success";
    }

    @RequestMapping("/?/one")
    public String one() {
        System.out.println("one...");
        return "success";
    }

    @RequestMapping("/*/two")
    public String two() {
        System.out.println("two...");
        return "success";
    }

    @RequestMapping("/**/three")
    public String three() {
        System.out.println("three...");
        return "success";
    }


}