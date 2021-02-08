package com.joezhou.springboot2webflux.controller;

import com.joezhou.springboot2webflux.pojo.User;
import com.joezhou.springboot2webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author JoeZhou
 */
@RestController
@RequestMapping("/api/webflux")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/select-by-id")
    public Mono<User> selectById(Integer id) {
        return userService.selectById(id);
    }

    @RequestMapping(value = "/select-all", produces = "application/stream+json")
    public Flux<User> selectAll() {
        return userService.selectAll().delayElements(Duration.ofSeconds(2));
    }

}