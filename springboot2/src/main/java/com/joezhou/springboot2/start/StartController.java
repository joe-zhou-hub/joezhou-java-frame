package com.joezhou.springboot2.start;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JoeZhou
 */
@RestController
@RequestMapping("api/start")
public class StartController {

    @RequestMapping("test")
    public String test() {
        return "test()...";
    }

}
