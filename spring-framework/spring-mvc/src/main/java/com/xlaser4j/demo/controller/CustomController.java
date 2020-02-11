package com.xlaser4j.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.xlaser4j.demo.annotation.XController;
import com.xlaser4j.demo.annotation.XRequestMapping;
import com.xlaser4j.demo.annotation.XRequestParam;
import lombok.extern.slf4j.Slf4j;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2018/9/18 20:25
 * @description:
 * @modified: Elijah.D
 */
@Slf4j
@XController
@XRequestMapping("/x")
public class CustomController {
    /**
     * Test one.
     *
     * @param response
     * @param param
     * @throws IOException
     */
    @XRequestMapping("/a")
    public void testOne(HttpServletResponse response, @XRequestParam("param") String param) throws IOException {

        response.getWriter().write("this is test one param: " + param);
    }

    /**
     * Test two.
     *
     * @param response
     */
    @XRequestMapping("/c")
    public void testThree(HttpServletResponse response) throws IOException {

        response.getWriter().write("this is test two !");
    }
}
