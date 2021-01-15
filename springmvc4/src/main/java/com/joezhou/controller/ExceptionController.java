package com.joezhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JoeZhou
 */
@RequestMapping("/api/exception")
@Controller
public class ExceptionController {

    @ResponseBody
    @RequestMapping("test")
    public String test(Integer num) {
        System.out.println(1 / num);
        return "ok";
    }

    @ResponseBody
    @ExceptionHandler({ArithmeticException.class, ArrayIndexOutOfBoundsException.class})
    public String exceptionHandler(Exception e) {
        System.out.println("exceptionHandler()...");
        e.printStackTrace();
        return "err";
    }
}