package com.xlaser4j.resilience4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @package: com.xlaser4j.resilience4j
 * @author: Elijah.D
 * @time: 2020/3/29 18:24
 * @description:
 * @modified: Elijah.D
 */
@SpringBootApplication
public class Resilience4jConsumerApplication {
    public static void main(String[] args) {
        System.out.println("======================= Resilience4j是cloud在G版推荐的官方容错组件,是github开源项目,同时也是由于hystrix闭源的替代组件 =======================");
        System.out.println("======================= Resilience4j各个功能的依赖可以按需导入,Basic模块做了简单测试(spring-boot2引入的依赖根据版本不同,或许需要强制配置信息,可以排除依赖处理:如,exclude:circuitBreaker) =======================");
        System.out.println("======================= Resilience4j可以编程式使用,如Basic模块中,通过代码配置相关功能,也可以通过注解声明式使用 =======================");
        SpringApplication.run(Resilience4jConsumerApplication.class);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
