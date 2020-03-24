package com.xlaser4j.hystrix.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.hystrix.controller
 * @author: Elijah.D
 * @time: 2020/3/25 14:29
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class ProviderController {
    /**
     * 测试hystrix fallback
     *
     * @return msg
     */
    @GetMapping("/fb")
    public String testConsumerFallBack() {
        return "Hystrix Provider";
    }

    /**
     * 测试hystrix缓存
     *
     * @param param1 param1
     * @param param2 param2
     * @return msg
     */
    @GetMapping("/c")
    public String testConsumerCache(String param1, String param2) {
        return param1 + param2;
    }
}
