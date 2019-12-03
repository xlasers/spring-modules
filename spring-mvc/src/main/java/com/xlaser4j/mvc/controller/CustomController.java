package com.xlaser4j.mvc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.xlaser4j.mvc.annotation.XController;
import com.xlaser4j.mvc.annotation.XRequestMapping;
import com.xlaser4j.mvc.annotation.XRequestParam;
import com.xlaser4j.mvc.common.LogConsts;
import lombok.extern.slf4j.Slf4j;

/**
 * @package: com.xlaser4j.spring.controller
 * @author: Elijah.D
 * @time: 2018/9/18 20:25
 * @description:
 * @copyright: Copyright(c) 2018
 * @version: V1.0
 * @modified: Elijah.D
 */
@Slf4j
@XController
@XRequestMapping("/x")
public class CustomController {
    /**
     * Test one.
     *
     * @param response the response
     * @param param    the param
     */
    @XRequestMapping("/testOne")
    public void testOne(HttpServletResponse response, @XRequestParam("param") String param) {

        log.info("【spring 自定义注解】 参数 param: {}", param);

        try {
            response.getWriter().write("this is test one ! param: " + param);
        } catch (IOException e) {

            log.error(LogConsts.IO_ERROR_MSG, e.getMessage());
        }
    }

    @XRequestMapping("/testTwo")
    public void testTwo(HttpServletResponse response, @XRequestParam("num") Integer num) {

        log.info("【spring 自定义注解】 多个参数 param: {}, num: {}", num);

        try {
            response.getWriter().write("this is test two ! param: " + num);
        } catch (IOException e) {

            log.error(LogConsts.IO_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test three.
     *
     * @param response the response
     */
    @XRequestMapping("/testThree")
    public void testThree(HttpServletResponse response) {

        log.info("【spring 自定义注解】 无参数 ...");

        try {
            response.getWriter().write("this is test three !");
        } catch (IOException e) {

            log.info(LogConsts.IO_ERROR_MSG, e.getMessage());
        }
    }
}
