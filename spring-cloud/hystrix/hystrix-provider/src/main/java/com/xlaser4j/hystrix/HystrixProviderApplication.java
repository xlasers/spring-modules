package com.xlaser4j.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @package: com.xlaser4j.hystrix
 * @author: Elijah.D
 * @time: 2020/3/24 18:25
 * @description:
 * @modified: Elijah.D
 */
@SpringBootApplication
public class HystrixProviderApplication {
    public static void main(String[] args) {
        System.out.println("\n================================ Provider用于测试,只需要与consumer保证同时注册到同一个注册中心即可,如:Eureka,Consul =================================\n");
        SpringApplication.run(HystrixProviderApplication.class);
    }
}
