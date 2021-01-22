package com.joezhou.controller;

import com.joezhou.pojo.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JoeZhou
 */
@RequestMapping("/api/valid/")
@Controller
public class HibernateValidatorController {

    @ResponseBody
    @RequestMapping("hibernate-validator")
    public String hibernateValid(@Validated(Teacher.GroupA.class) Teacher teacher,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("bindingResult: there is " + bindingResult.getErrorCount() + " exceptions...");
            for (ObjectError e : bindingResult.getAllErrors()) {
                System.out.println("bindingResult: exception from: " + e.getObjectName());
                System.out.println("bindingResult: exception message: " + e.getDefaultMessage());
            }
        } else {
            System.out.println("no errors...");
        }

        System.out.println(teacher);
        return "success";
    }
}