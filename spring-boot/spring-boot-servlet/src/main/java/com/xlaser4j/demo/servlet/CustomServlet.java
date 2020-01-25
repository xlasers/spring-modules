package com.xlaser4j.demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @package: com.xlaser4j.demo.servlet
 * @author: Elijah.D
 * @time: 2020/1/25 22:42
 * @description:
 * @modified: Elijah.D
 */
@WebServlet(urlPatterns = {"/servlet"})
public class CustomServlet extends HttpServlet {
    private static final long serialVersionUID = -5352774463202629198L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Test Servlet!");
    }
}
