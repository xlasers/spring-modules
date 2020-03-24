package com.xlaser4j.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @package: com.xlaser4j.hystrix
 * @author: Elijah.D
 * @time: 2020/3/24 18:23
 * @description:
 * @modified: Elijah.D
 */
@SpringCloudApplication
public class HystrixConsumerApplication {
    public static void main(String[] args) {
        System.out.println("\n================================ SpringCloud是个组合注解,Hystrix增加微服务的容错性,用于服务降级防止服务雪崩 =================================\n");
        System.out.println("\n================================ 也即是当某一个服务故障时,通过Hystrix提前配置的策略来保证不阻断整个系统的运行 =================================\n");
        System.out.println("\n================================ Hystrix使用有两种方式:注解,请求命令,通常使用注解 =================================\n");
        SpringApplication.run(HystrixConsumerApplication.class);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
