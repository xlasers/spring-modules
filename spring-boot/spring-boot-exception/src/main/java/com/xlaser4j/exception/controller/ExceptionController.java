package com.xlaser4j.exception.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.exception.controller
 * @author: Elijah.D
 * @time: 2020/1/22 20:58
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class ExceptionController {
    /**
     * test exception
     */
    @RequestMapping("/a")
    public void testException() {
        throw new ArithmeticException("Error Test!");
    }
}
