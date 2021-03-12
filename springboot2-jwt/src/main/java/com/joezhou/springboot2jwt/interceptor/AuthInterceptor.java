package com.joezhou.springboot2jwt.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.joezhou.springboot2jwt.annotation.TokenAuth;
import com.joezhou.springboot2jwt.util.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author JoeZhou
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object obj) throws IOException {

        // 若请求URL不指向动作方法，直接放行
        if (!(obj instanceof HandlerMethod)) {
            return true;
        }

        // 若动作方法上没有标记@TokenAuth，直接放行
        Method method = ((HandlerMethod) obj).getMethod();
        if (!method.isAnnotationPresent(TokenAuth.class)) {
            return true;
        }

        // 从请求头中获取token，若没获取成功，尝试从请求参数中获取，若均失败返回错误。
        String token = req.getHeader("token");
        if (token == null || "".equals(token)) {
            token = req.getParameter("token");
            if (token == null || "".equals(token)) {
                throw new RuntimeException("token is null...");
            }
        }

        DecodedJWT decodedJwt = TokenUtil.verify(token);
        if (decodedJwt == null) {
            throw new RuntimeException("token verify error...");
        }
        int id = decodedJwt.getClaim("id").asInt();
        String username = decodedJwt.getClaim("username").asString();
        String avatar = decodedJwt.getClaim("avatar").asString();
        req.setAttribute("id", id);
        req.setAttribute("username", username);
        req.setAttribute("avatar", avatar);
        return true;
    }
}