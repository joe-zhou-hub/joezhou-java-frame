package com.joezhou.controller;

import com.joezhou.util.MyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author JoeZhou
 */
@RequestMapping("/api/exception")
@Controller
public class ExceptionController {

    @ResponseBody
    @RequestMapping("exception-handler-test")
    public String exceptionHandlerTest(Integer num) {
        System.out.println("test()...");
        System.out.println(1 / num);
        int[] arr = {1, 2};
        System.out.println(arr[num]);
        return "success";
    }

    @ResponseBody
    @ExceptionHandler(ArithmeticException.class)
    public String exceptionHandler(Exception e) {
        System.out.println("ExceptionController.exceptionHandler()..." + e);
        return "error";
    }

    @RequestMapping("response-status-test")
    public String responseStatusTest(Integer num) {
        System.out.println("responseStatusTest()...");
        return num == 0 ? "forward:response-status" : "success";
    }

    @RequestMapping("response-status")
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "ExceptionController: 页面走丢了！")
    public void responseStatus() {
        System.out.println("responseStatus()...");
    }

    @ResponseBody
    @RequestMapping("my-not-found-exception-test")
    public String myNotFoundExceptionTest(Integer num) throws MyNotFoundException {
        System.out.println("myNotFoundExceptionTest()...");
        if (num == 0) {
            throw new MyNotFoundException();
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping("xml-exception-test")
    public String xmlExceptionTest(Integer num) {
        System.out.println("xmlExceptionTest()...");
        if (num == 0) {
            throw new NullPointerException();
        }
        return "success";
    }
}