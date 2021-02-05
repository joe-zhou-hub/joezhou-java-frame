package com.joezhou.springboot2webflux.controller;

import com.joezhou.springboot2webflux.pojo.User;
import com.joezhou.springboot2webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author JoeZhou
 */
@RestController
@RequestMapping("/api/user")
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

    @RequestMapping("/select-all")
    public Flux<User> selectAll() {
        return userService.selectAll();
    }

}