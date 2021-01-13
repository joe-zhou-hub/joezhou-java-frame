package com.joezhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author JoeZhou
 */
@RequestMapping("/api/scope")
@Controller
public class ScopeController {

    @RequestMapping("servlet")
    public String servlet(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
        System.out.println("request: " + req);
        System.out.println("response: " + resp);
        System.out.println("session: " + session);
        System.out.println("application: " + req.getServletContext());
        return "success";
    }

    @RequestMapping("request-scope")
    public ModelAndView requestScope(ModelAndView mv, Model model,
                                     ModelMap modelMap, Map<String, Object> map) {
        mv.addObject("key-mv", "value-mv");
        model.addAttribute("key-model", "value-model");
        modelMap.addAttribute("key-model-map", "value-model-map");
        map.put("key-map", "value-map");

        mv.setViewName("forward:/view/success.jsp");
        return mv;
    }

}