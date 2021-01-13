package com.joezhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author JoeZhou
 */
@RequestMapping("/api")
@Controller
public class ServletController {

    @RequestMapping("servlet")
    public String servlet(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
        System.out.println("request: " + req);
        System.out.println("response: " + resp);
        System.out.println("session: " + session);
        System.out.println("application: " + req.getServletContext());
        return "success";
    }

}