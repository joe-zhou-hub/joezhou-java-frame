package com.joezhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author JoeZhou
 */
@RequestMapping("/api/param")
@Controller
public class ParamController {

    @RequestMapping("cookie")
    public String cookieValue(@CookieValue(value = "name", required = false, defaultValue = "admin") String cookieName) {
        System.out.println("name: " + cookieName);
        return "success";
    }

    @RequestMapping("request-header")
    public String requestHeader(@RequestHeader(value = "host") String requestHeaderHost) {
        System.out.println("host: " + requestHeaderHost);
        return "success";
    }

    @RequestMapping("rest/{id}/{age}")
    public String pathVariable(@PathVariable("id") int restA, @PathVariable("age") int restB) {
        System.out.println("id: " + restA);
        System.out.println("age: " + restB);
        return "success";
    }

    @RequestMapping("kv")
    public String requestParam(@RequestParam("user-name") String name, @RequestParam("user-age") int age) {
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        return "success";
    }
}