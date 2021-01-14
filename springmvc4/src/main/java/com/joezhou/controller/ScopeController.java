package com.joezhou.controller;

import com.joezhou.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author JoeZhou
 */
@SessionAttributes(value = {"name", "info"}, types = Integer.class)
@RequestMapping("/api/scope")
@Controller
public class ScopeController {

    @RequestMapping("request-scope")
    public ModelAndView requestScope(
            HttpServletRequest req, ModelAndView mv, Model model,
            ModelMap modelMap, Map<String, Object> map) {

        req.setAttribute("key-request", "value-request");
        mv.addObject("key-mv", "value-mv");
        model.addAttribute("key-model", "value-model");
        modelMap.addAttribute("key-model-map", "value-model-map");
        map.put("key-map", "value-map");
        // forward:避免拼接前后缀
        mv.setViewName("forward:/view/request-value.jsp");
        return mv;
    }

    @RequestMapping("session-scope")
    public ModelAndView sessionScope(HttpSession session, ModelAndView mv) {
        session.setAttribute("id", "9527");
        mv.addObject("name", "admin");
        mv.addObject("gender", 1);
        mv.addObject("age", 18);
        mv.addObject("info", "管理员");
        mv.setViewName("forward:/view/session-value.jsp");
        return mv;
    }

    @RequestMapping("model-attribute")
    public void modelAttribute(User user, Map<String, Object> map) {
        System.out.println("modelAttribute(): " + user);
        System.out.println("map: " + map.get("key"));
    }

    @ModelAttribute
    public void before(User user, Map<String, Object> map) {
        System.out.println("before(): " + user);
        map.put("key", "before()");
    }

}