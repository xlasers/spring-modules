package com.xlaser4j.zuul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.zuul.controller
 * @author: Elijah.D
 * @time: 2020/3/31 13:33
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class ProviderController {
    @GetMapping("/test")
    public String testProxy() {
        return "Hello Zuul, 测试zuul的代理功能!";
    }

    @GetMapping("/ignore")
    public String testIgnore() {
        return "Hello Zuul, 测试zuul的代理ignore功能!";
    }
}
