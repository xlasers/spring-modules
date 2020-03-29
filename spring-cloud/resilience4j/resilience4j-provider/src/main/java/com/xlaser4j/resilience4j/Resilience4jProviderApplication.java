package com.xlaser4j.resilience4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @package: com.xlaser4j.resilience4j
 * @author: Elijah.D
 * @time: 2020/3/29 18:25
 * @description:
 * @modified: Elijah.D
 */
@SpringBootApplication
public class Resilience4jProviderApplication {
    public static void main(String[] args) {
        System.out.println("======================= Resilience4j的限流组件,主要用于服务提供方的限流保护,这里引入RateLimiter =======================");
        System.out.println("======================= 注意springboot项目中,根据版本不同,或许需要排除依赖: 如引入circuitBreaker依赖之后必须配置circuitBreaker相关信息,否则报错,可以exclude排除circuit依赖 =======================");
        SpringApplication.run(Resilience4jProviderApplication.class);
    }
}
