package com.joezhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author JoeZhou
 */
@RequestMapping("/api/param")
@Controller
public class ParamController {

    @RequestMapping("request-header")
    public String requestHeader(@RequestHeader("host") String host) {
        System.out.println(host);
        return "success";
    }

    @RequestMapping("cookie")
    public String cookieValue(@CookieValue("name") String value) {
        System.out.println(value);
        return "success";
    }

    @RequestMapping("rest/{id}/{age}")
    public String pathVariable(@PathVariable("id") Integer a, @PathVariable("age") Integer b) {
        System.out.println(a);
        System.out.println(b);
        return "success";
    }

    @RequestMapping("kv")
    public String requestParam(
            @RequestParam("user-name") String name,
            @RequestParam(value = "user-age", required = false, defaultValue = "18") int age) {
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        return "success";
    }
}