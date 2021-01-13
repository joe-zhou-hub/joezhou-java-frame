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

    @RequestMapping({"value-a", "value-b"})
    public String value() {
        System.out.println("value()...");
        return "success";
    }

    @RequestMapping("/**/user-*/?")
    public String valueMatch() {
        System.out.println("valueMatch()...");
        return "success";
    }

    @RequestMapping(value = "/method", method = RequestMethod.GET)
    public String method() {
        System.out.println("method()...");
        return "success";
    }


    @RequestMapping(value = "/params", params = {"!name", "age=18", "gender!=1"})
    public String params() {
        System.out.println("params()...");
        return "success";
    }

}