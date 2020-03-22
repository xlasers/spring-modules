package com.xlaser4j.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @package: com.xlaser4j.eureka
 * @author: Elijah.D
 * @time: 2020/3/21 20:14
 * @description:
 * @modified: Elijah.D
 */
@SpringBootApplication
public class EurekaConsumerApplication {
    public static void main(String[] args) {
        System.out.println("\n================================ consumer是一个Eureka-Client旧版需要注解enable,新版本不需要默认就开启 =================================\n");
        System.out.println("\n================================ 注意@LoadBalanced注解实现的[客户端]负载均衡原理 =================================\n");
        SpringApplication.run(EurekaConsumerApplication.class, args);
    }
}
