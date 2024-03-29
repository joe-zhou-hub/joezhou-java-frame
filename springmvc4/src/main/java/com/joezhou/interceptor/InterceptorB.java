package com.joezhou.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JoeZhou
 */
public class InterceptorB implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        System.out.println("InterceptorB:preHandle()...");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp,Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("InterceptorB:postHandle()...");
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) throws Exception {
        System.out.println("InterceptorB:afterCompletion()...");
    }
}