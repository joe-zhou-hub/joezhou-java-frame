package com.joezhou.controller;

import com.joezhou.pojo.User;
import com.joezhou.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author JoeZhou
 */
@RequestMapping("/api/param")
@Controller
public class ParamController {

    @RequestMapping("cookie-value")
    public String cookieValue(@CookieValue(value = "name", required = false, defaultValue = "admin") String cookieName) {
        System.out.println("name: " + cookieName);
        return "success";
    }

    @RequestMapping("request-header")
    public String requestHeader(@RequestHeader(value = "host") String requestHeaderHost) {
        System.out.println("host: " + requestHeaderHost);
        return "success";
    }

    @RequestMapping("path-variable/{id}/{age}")
    public String pathVariable(@PathVariable("id") Integer restA, @PathVariable("age") Integer restB) {
        System.out.println("id: " + restA);
        System.out.println("age: " + restB);
        return "success";
    }

    @RequestMapping("request-param")
    public String requestParam(
            @RequestParam("username") String name,
            Integer age, Boolean gender,Integer[] ids,
            User userA, User userB,
            UserVo userVoA, UserVo userVoB) {
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        System.out.println("gender: " + gender);
        System.out.println("ids: " + Arrays.toString(ids));
        System.out.println("userA: " + userA);
        System.out.println("userB: " + userB);
        System.out.println("userVoA: " + userVoA);
        System.out.println("userVoB: " + userVoB);
        return "success";
    }
}