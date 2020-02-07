package com.xlaser4j.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/2/7 11:40
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class TestController {
    /**
     * 测试正常情况
     *
     * @return
     */
    @GetMapping("/b")
    public String testNormal() {
        return "测试正常请求,admin的监控情况!";
    }
}
