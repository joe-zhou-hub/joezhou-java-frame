package com.joezhou.springboot2security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JoeZhou
 */
@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException {
        System.out.println("CustomLoginFailureHandler...");
        resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write("login fail!");
    }
}