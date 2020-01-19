package com.xlaser4j.advice.advice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @package: com.xlaser4j.advice.exception
 * @author: Elijah.D
 * @time: 2020/1/19 13:21
 * @description:
 * @modified: Elijah.D
 */
@ControllerAdvice
public class ApiExceptionHandler {
    /**
     * 异常处理,可以定义父类Exception,方法内instance of判断后单独处理封装返回统一ApiResponse
     *
     * @param e
     */
    @ExceptionHandler(NullPointerException.class)
    public void handleNullPointerException(NullPointerException e, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("通过controllerAdvice拦截NPE,手动封装处理空指针异常!");
        out.flush();
        out.close();
    }
}
