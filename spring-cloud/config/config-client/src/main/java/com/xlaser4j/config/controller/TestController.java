package com.xlaser4j.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.config.controller
 * @author: Elijah.D
 * @time: 2020/4/20 21:17
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class TestController {
    @Value("${config-client-test}")
    private String key;

    @GetMapping("/r")
    public String readConfig() {
        return key;
    }
}
