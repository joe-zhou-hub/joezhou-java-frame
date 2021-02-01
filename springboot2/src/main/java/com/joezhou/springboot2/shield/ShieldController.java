package com.joezhou.springboot2.shield;

import com.joezhou.shieldspringbootstarter.ShieldOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JoeZhou
 */
@RequestMapping("api/shield")
@RestController
public class ShieldController {

    private ShieldOperations shieldOperations;

    @Autowired
    public ShieldController(ShieldOperations shieldOperations) {
        this.shieldOperations = shieldOperations;
    }

    @RequestMapping("test")
    public String test(String word) {
        return shieldOperations.shield(word);
    }
}