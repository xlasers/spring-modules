package com.xlaser4j.eureka.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.eureka.controller
 * @author: Elijah.D
 * @time: 2020/3/22 15:13
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class ProviderController {
    /**
     * jar包改变端口启动两个实例注册到eureka-server上
     */
    @Value("${server.port}")
    public Integer port;

    /**
     * 模拟生产者
     *
     * @return
     */
    @GetMapping("/ep")
    public String provider() {
        return "Hello Consumer! 端口: " + port;
    }
}
