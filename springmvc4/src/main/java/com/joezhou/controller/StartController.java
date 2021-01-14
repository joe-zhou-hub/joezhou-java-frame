package com.joezhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author JoeZhou
 */
@Controller
public class StartController {

    @RequestMapping("/api/start")
    public void start() {
        System.out.println("start()...");
    }

}