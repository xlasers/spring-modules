package com.xlaser4j.advice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.advice.controller
 * @author: Elijah.D
 * @time: 2020/1/19 13:35
 * @description: 处理controller的异常
 * @modified: Elijah.D
 */
@RestController
public class ExceptionController {
    /**
     * 测试异常处理
     */
    @GetMapping("/c")
    public void testExceptionHandler() {

        throw new NullPointerException();
    }
}
