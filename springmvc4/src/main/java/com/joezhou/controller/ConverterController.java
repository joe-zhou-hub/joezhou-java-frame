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
        return "ok";
    }

    @ResponseBody
    @RequestMapping("date-time-format")
    public String dateTimeFormat(Student student, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println("爆发了" + result.getErrorCount() + "个异常！");
            for (ObjectError e : result.getAllErrors()) {
                System.out.println("爆发异常的对象是：" + e.getObjectName());
                System.out.println("具体异常的内容为：" + e.getDefaultMessage());
            }
        }
        System.out.println(student);
        return "ok";
    }

}