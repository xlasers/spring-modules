package com.xlaser4j.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;

/**
 * {@link org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration}
 * 异常数据信息:{@link org.springframework.boot.web.servlet.error.DefaultErrorAttributes}
 * 异常视图解析器:{@link org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver#resolveErrorView(HttpServletRequest, HttpStatus, Map)}
 *
 * @package: com.xlaser4j.exception
 * @author: Elijah.D
 * @time: 2020/0/0 00:00
 * @description: Start App Template
 * @modified: Elijah.D
 */
@SpringBootApplication
public class ExceptionApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================spring异常处理源码类ErrorMvcAutoConfiguration==============================\n");
        System.out.println("\n==============================spring异常默认查找异常页面优先级:优先精确匹配状态码,然后是动态页面,然后是静态页面==============================\n");
        SpringApplication.run(ExceptionApplication.class, args);
    }
}
