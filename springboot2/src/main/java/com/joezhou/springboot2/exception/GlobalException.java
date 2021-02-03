package com.joezhou.springboot2.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JoeZhou
 */
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> exception(Exception e) {
        System.out.println("GlobalException.exception()...");
        Map<String, Object> exceptionMsg = new HashMap<>(2);
        exceptionMsg.put("status", 500);
        exceptionMsg.put("msg", e.getMessage());
        return exceptionMsg;
    }
}