package com.joezhou.springboot2jwt.controller;

import com.joezhou.springboot2jwt.annotation.TokenAuth;
import com.joezhou.springboot2jwt.entity.User;
import com.joezhou.springboot2jwt.service.UserService;
import com.joezhou.springboot2jwt.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("login")
    public String login(User user) {
        User userFromDb = userService.login(user);
        if (userFromDb != null) {
            return TokenUtil.build(userFromDb);
        }
        return "login fail...";
    }

    @TokenAuth
    @RequestMapping("execute")
    public String execute() {
        return "execute()...";
    }
}