package com.joezhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author JoeZhou
 */
@RequestMapping("/api/scope")
@Controller
public class ScopeController {

    @RequestMapping("request-scope")
    public ModelAndView requestScope(ModelAndView mv, Model model, ModelMap modelMap, Map<String, Object> map) {
        mv.addObject("key-mv", "value-mv");
        model.addAttribute("key-model", "value-model");
        modelMap.addAttribute("key-model-map", "value-model-map");
        map.put("key-map", "value-map");
        // forward:避免拼接前后缀
        mv.setViewName("forward:/view/success.jsp");
        return mv;
    }

}