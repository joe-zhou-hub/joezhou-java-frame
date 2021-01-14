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
    public void value() {
        System.out.println("value()...");
    }

    @RequestMapping("/**/user-*/?")
    public void valueMatch() {
        System.out.println("valueMatch()...");
    }

    @RequestMapping(value = "/method", method = RequestMethod.GET)
    public void method() {
        System.out.println("method()...");
    }

    @RequestMapping(value = "/params", params = {"!name", "age=18", "gender!=1"})
    public void params() {
        System.out.println("params()...");
    }

}