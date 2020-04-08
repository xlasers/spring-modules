package com.xlaser4j.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.gateway.controller
 * @author: Elijah.D
 * @time: 2020/4/8 21:20
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class TestController {
    /**
     * 开启gateway的自动转发功能:spring.cloud.gateway.discovery.locator.enabled
     * <p>
     * gateway,provider同时注册到consul/eureka服务中心上,访问gateway服务加注册上的服务名即可路由到对应的服务上
     * <p>
     * http://localhost:8095/gateway-provider/gate ==> http://localhost:8094/gate
     *
     * @return
     */
    @GetMapping("/gate")
    public String testGateway() {
        return "测试路径转发路由功能";
    }

    /**
     * 也可以直接设置uri转发(loadBalance): lb:/gateway-provider
     * <p>
     * http://localhost:8095/filter ==> http://localhost:8094/filter
     * <p>
     * 通过服务名同样可以实现效果
     * http://localhost:8095/gateway-provider/filter ==> http://localhost:8094/filter
     *
     * @return
     */
    @GetMapping("/filter")
    public String testFilter(String name) {
        return "测试路径转发路由功能,并且通过过滤器自动添加参数name: " + name;
    }
}
