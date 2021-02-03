package com.joezhou.springboot2.servlet.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JoeZhou
 */
public class ContextFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("ContextFilter init()...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        System.out.println("ContextFilter doFilter(): " + req.getRequestURI());
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("ContextFilter destroy()...");
    }

}