package com.xlaser4j.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/1/22 21:54
 * @description: Test
 * @modified: Elijah.D
 */
@RestController
public class InterceptorController {
    /**
     * test interceptor
     */
    @RequestMapping("/a")
    public String testA() {
        return "Hello Interceptor!";
    }

    /**
     * test no interceptor
     */
    @RequestMapping("/b")
    public String testB() {
        return "Hello No Interceptor!";
    }
}
