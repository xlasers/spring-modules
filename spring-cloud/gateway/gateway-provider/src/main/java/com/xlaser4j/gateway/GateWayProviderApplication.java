package com.xlaser4j.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @package: com.xlaser4j.gateway
 * @author: Elijah.D
 * @time: 2020/4/1 21:25
 * @description:
 * @modified: Elijah.D
 */
@SpringBootApplication
public class GateWayProviderApplication {
    public static void main(String[] args) {
        System.out.println("============ 统一注册到consul上,访问gateway-provider服务名就自动路由转发到对应的服务上 ============");
        System.out.println("============ eg: 访问proxy代理端口加服务名gateway-provider(注意eureka注册的服务名大小写),路径转发到对应的服务上,http://localhost:8095/gateway-provider/gate ============");
        SpringApplication.run(GateWayProviderApplication.class);
    }
}
