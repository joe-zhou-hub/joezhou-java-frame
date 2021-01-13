package com.joezhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author JoeZhou
 */
@RequestMapping("/api/request-mapping")
@Controller
public class RequestMappingController {

    @RequestMapping({"test-a", "test-b", "test-c"})
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

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get() {
        System.out.println("get...");
        return "success";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String post() {
        System.out.println("post...");
        return "success";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete() {
        System.out.println("delete...");
        return "success";
    }

}