package com.joezhou.springboot2.exception;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JoeZhou
 */
@RestController
@RequestMapping("/api/exception")
public class ExceptionController {

    @RequestMapping("/execute")
    public String execute(Integer meta) {
        if (meta == 0) {
            throw new RuntimeException("execute() exception...");
        }
        return "success";
    }
}
