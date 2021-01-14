package com.joezhou.controller;

import com.joezhou.pojo.Student;
import com.joezhou.util.JsonData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author JoeZhou
 */
@RequestMapping("/api/forward")
@Controller
public class ForwardController {

    @RequestMapping("request")
    public void requestForward(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("requestForward()...");
        req.getRequestDispatcher("/api/forward/redirect-to-response-writer").forward(req, resp);
    }

    @RequestMapping("redirect-to-response-writer")
    public void responseRedirect(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("responseRedirect()...");
        resp.sendRedirect(req.getContextPath() + "/api/forward/response-writer");
    }

    @RequestMapping("response-writer")
    public void responseWriter(HttpServletResponse resp) throws IOException {
        System.out.println("responseWriter()...");
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print("response-writer");
    }

    @RequestMapping("string")
    public String stringForward() {
        System.out.println("stringForward()...");
        return "forward:redirect-to-model-and-view";
    }

    @RequestMapping("redirect-to-model-and-view")
    public String stringRedirect() {
        System.out.println("stringRedirect()...");
        return "redirect:/api/forward/model-and-view";
    }

    @RequestMapping("model-and-view")
    public ModelAndView modelAndView(ModelAndView modelAndView) {
        System.out.println("modelAndView()...");
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("response-body-json")
    public JsonData responseBodyJson() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "赵四", new Date(1_111_111_111L)));
        students.add(new Student(2, "刘能", new Date(9_999_999_999L)));
        students.add(new Student(3, "大脚", null));
        return new JsonData(students);
    }

    @ResponseBody
    @RequestMapping(value = "response-body-string", produces = "text/html;charset=utf-8")
    public String responseBodyString() {
        return "中文";
    }

}