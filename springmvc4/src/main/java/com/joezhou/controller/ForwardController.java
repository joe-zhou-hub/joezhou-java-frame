package com.joezhou.controller;

import com.joezhou.pojo.Student;
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
    @RequestMapping("jackson")
    public List<Student> jackson() {
        System.out.println("jackson()...");

        List<Student> students = new ArrayList<>();
        Student student01 = new Student();
        student01.setId(1);
        student01.setName("赵桑");
        student01.setBirthday(new Date());
        Student student02 = new Student();
        student02.setId(2);
        student02.setBirthday(new Date());

        students.add(student01);
        students.add(student02);
        return students;
    }

}