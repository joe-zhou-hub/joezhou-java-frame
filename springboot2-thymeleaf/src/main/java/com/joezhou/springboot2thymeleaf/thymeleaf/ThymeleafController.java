package com.joezhou.springboot2thymeleaf.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author JoeZhou
 */
@Controller
@RequestMapping("api/thymeleaf")
public class ThymeleafController {

    @RequestMapping("test")
    public String test(Map<String, Object> map, String name) {
        map.put("msg", name);
        // /templates/thymeleaf-test.html
        return "thymeleaf-test";
    }
}