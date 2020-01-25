package com.xlaser4j.demo.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

/**
 * @package: com.xlaser4j.demo.filter
 * @author: Elijah.D
 * @time: 2020/1/25 22:44
 * @description:
 * @modified: Elijah.D
 */
@WebFilter(urlPatterns = {"/web", "/filter"})
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("Test Filter Init!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Test Filter Do Filter!");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("Test Filter Destroy!");
    }
}
