package com.joezhou.springboot2.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author JoeZhou
 */
public class AuthInterceptor implements HandlerInterceptor {

    private UserService userService;

    @Autowired
    public AuthInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        // 如果方法上有 @PassToken，跳过认证，直接放行
        if (method.isAnnotationPresent(PassToken.class)) {
            return true;
        }

        // 如果方法上有 @LoginAuth，执行认证
        if (method.isAnnotationPresent(LoginAuth.class)) {

            // 从请求头中取出 token
            String token = httpServletRequest.getHeader("token");
            if (token == null) {
                throw new RuntimeException("token is null, plz re-login...");
            }

            // 获取 token 中的 user id
            String username, password;
            try {
                DecodedJWT decodedJwt = JWT.decode(token);
                username = decodedJwt.getClaim("username").toString();
                password = decodedJwt.getClaim("password").toString();
                System.out.println(username + "::::" + password);
            } catch (JWTDecodeException j) {
                throw new RuntimeException("401: token decode error...");
            }

            User userFromDb = userService.login(new User(null, username, password, null));
            if (userFromDb == null) {
                throw new RuntimeException("500: login error, plz re-login...");
            }

            // 验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userFromDb.getPassword())).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new RuntimeException("401: token verify error...");
            }
            return true;
        }

        return true;
    }
}