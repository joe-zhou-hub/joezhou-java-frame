package com.joezhou.springboot2.aop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JoeZhou
 */
@RestController
@RequestMapping("/api/aop")
public class AopController {

    @RequestMapping("execute")
    public String execute() {
        System.out.println("execute()...");
        return "success";
    }
}
