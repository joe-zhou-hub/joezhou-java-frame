package com.joezhou.springboot2jwt.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author JoeZhou
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private UserService userService;

    @Autowired
    public AuthInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object obj) {

        // if url is reference controller method
        if (obj instanceof HandlerMethod) {
            Method method = ((HandlerMethod)obj).getMethod();

            // check token if the method is marked with @LoginAuth
            if (method.isAnnotationPresent(TokenAuth.class)) {

                String token = req.getHeader("token");
                if (token == null) {
                    throw new RuntimeException("token is null...");
                }

                String username, password;
                try {
                    DecodedJWT decodedJwt = JWT.decode(token);
                    username = decodedJwt.getClaim("username").asString();
                    password = decodedJwt.getClaim("password").asString();
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("token decode error...");
                }

                User user = userService.login(new User(null, username, password, null));
                if (user == null) {
                    throw new RuntimeException("login error...");
                }

                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("token verify error...");
                }
            }
        }

        return true;
    }
}