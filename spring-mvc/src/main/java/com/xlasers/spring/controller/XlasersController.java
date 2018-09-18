package com.xlasers.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.xlasers.spring.annotation.XController;
import com.xlasers.spring.annotation.XRequestMapping;
import com.xlasers.spring.annotation.XRequestParam;
import lombok.extern.slf4j.Slf4j;

import static com.xlasers.spring.common.LogConsts.IO_ERROR_MSG;

/**
 * The type Xlasers controller.
 *
 * @package: com.Xlasers.spring.controller
 * @author: Elijah.D
 * @time: CreateAt 2018/9/18 && 20:25
 * @description:
 * @copyright: Copyright © 2018 Xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Slf4j
@XController
@XRequestMapping("/x")
public class XlasersController {
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

            log.error(IO_ERROR_MSG, e.getMessage());
        }
    }

    @XRequestMapping("/testTwo")
    public void testTwo(HttpServletResponse response ,@XRequestParam("num") Integer num) {

        log.info("【spring 自定义注解】 多个参数 param: {}, num: {}", num);

        try {
            response.getWriter().write("this is test two ! param: " + num );
        } catch (IOException e) {

            log.error(IO_ERROR_MSG, e.getMessage());
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
            response.getWriter().write("this is test three !" );
        } catch (IOException e) {

            log.info(IO_ERROR_MSG, e.getMessage());
        }
    }
}
