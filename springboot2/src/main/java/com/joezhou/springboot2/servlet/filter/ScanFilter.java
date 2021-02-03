package com.joezhou.springboot2.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JoeZhou
 */
@WebFilter("/api/servlet/*")
public class ScanFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("ScanFilter init()...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        System.out.println("ScanFilter doFilter(): " + req.getRequestURI());
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("ScanFilter destroy()...");
    }

}