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
public class EurekaProviderApplication {
    public static void main(String[] args) {
        System.out.println("\n================================ Provider是一个Eureka-Client旧版需要注解enable,新版本不需要默认就开启 =================================\n");
        SpringApplication.run(EurekaProviderApplication.class, args);
    }
}
