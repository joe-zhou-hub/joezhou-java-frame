package com.joezhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JoeZhou
 */
@RequestMapping("/api/forward")
@Controller
public class ForwardController {

    @RequestMapping("request-forward")
    public void requestForward(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("requestForward()...");
        req.getRequestDispatcher("/view/success.html").forward(req, resp);
    }

    @RequestMapping("response-redirect")
    public void responseRedirect(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("responseRedirect()...");
        resp.setContentType("text/html;charset=utf-8");
        resp.sendRedirect(req.getContextPath() + "/view/success.html");
    }

    @RequestMapping("response-writer")
    public void responseWriter(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("responseWriter()...");
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print("response-writer");
    }

    @RequestMapping("string-forward")
    public String stringForward() {
        System.out.println("stringForward()...");
        return "forward:string-redirect";
    }

    @RequestMapping("string-redirect")
    public String stringRedirect() {
        System.out.println("stringRedirect()...");
        return "redirect:/view/success.html";
    }

}