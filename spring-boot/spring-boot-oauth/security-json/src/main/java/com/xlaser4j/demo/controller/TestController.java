package com.xlaser4j.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/2/4 17:21
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class TestController {
    /**
     * 测试
     *
     * @return
     */
    @GetMapping("/a")
    public String testAdmin() {
        return "Hello Application/Json!";
    }
}
