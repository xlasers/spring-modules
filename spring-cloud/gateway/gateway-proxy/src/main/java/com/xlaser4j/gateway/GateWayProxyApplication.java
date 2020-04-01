package com.xlaser4j.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * 不需要添加web依赖,否则报错
 * Spring MVC found on classpath, which is incompatible with Spring Cloud Gateway at this time.
 * Please remove spring-boot-starter-web dependency.
 *
 * @package: com.xlaser4j.gateway
 * @author: Elijah.D
 * @time: 2020/4/1 21:25
 * @description:
 * @modified: Elijah.D
 */
@SpringBootApplication
public class GateWayProxyApplication {
    public static void main(String[] args) {
        System.out.println("============= Gateway是cloud为了替代zuul闭源推出的新的开源网关代理模块,引入gateway依赖即可配置(不需要web依赖,否则报错) =============");
        System.out.println("============= 可以通过bean编码式配置代理路径转发,也可以通过yml配置文件配置代理,测试NoMicroService/启动Provider测试MicroService =============");
        SpringApplication.run(GateWayProxyApplication.class);
    }

    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 配置路径映射,get转发到http://httpbin.org/get
                .route("byBean", predicateSpec -> predicateSpec.path("/get").uri("http://httpbin.org"))
                .build();
    }
}
