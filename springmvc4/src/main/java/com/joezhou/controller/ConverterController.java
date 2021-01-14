package com.joezhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author JoeZhou
 */
@RequestMapping("/api/converter")
@Controller
public class ConverterController {

    @ResponseBody
    @RequestMapping("string-to-date")
    public String stringToDate(Date date) {
        System.out.println("stringToDate()...");
        System.out.println(date);
        return "ok";
    }

}