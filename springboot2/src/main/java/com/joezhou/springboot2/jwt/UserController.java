package com.joezhou.springboot2.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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

        if (userFromDb == null) {
            return "login error...";
        } else {
            String token = JWT.create()
                    .withClaim("username", userFromDb.getUsername())
                    .withClaim("password", userFromDb.getPassword())
                    .sign(Algorithm.HMAC256(userFromDb.getPassword()));
            return "token: " + token
                    + " id: " + userFromDb.getId()
                    + " password: " + userFromDb.getPassword()
                    + " avatar: " + userFromDb.getAvatar();
        }
    }

    @LoginAuth
    @RequestMapping("login-auth")
    public String loginAuth() {
        return "loginAuth...";
    }

    @PassToken
    @RequestMapping("pass-token")
    public String passToken() {
        return "passToken()...";
    }
}