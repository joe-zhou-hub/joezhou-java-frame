package com.joezhou.controller;

import com.joezhou.pojo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
    public String stringToDate(Date date, String name) {
        System.out.println(date);
        System.out.println(name);
        return "success";
    }

    @ResponseBody
    @RequestMapping("date-time-format")
    public String dateTimeFormat(Student student, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("bindingResult: there is " + bindingResult.getErrorCount() + " exceptions...");
            for (ObjectError e : bindingResult.getAllErrors()) {
                System.out.println("bindingResult: exception from: " + e.getObjectName());
                System.out.println("bindingResult: exception message: " + e.getDefaultMessage());
            }
        } else {
            System.out.println("no errors...");
        }

        System.out.println(student);
        return "success";
    }

}