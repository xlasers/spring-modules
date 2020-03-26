package com.xlaser4j.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @package: com.xlaser4j.feign
 * @author: Elijah.D
 * @time: 2020/3/26 11:27
 * @description:
 * @modified: Elijah.D
 */
@SpringBootApplication
@EnableFeignClients
public class FeignConsumerApplication {
    public static void main(String[] args) {
        System.out.println("======================================= RestTemplate是spring-mvc提供的远程调用工具,但是在微服务使用过程,存在大量模板代码,重复低下,通过feign可以大大简化这一过程 =======================================");
        System.out.println("======================================= 由于netflix闭源问题,feign有springCloud团队开发继续维护并且更名为openfeign =======================================");
        System.out.println("======================================= 注解开启feignClients客户端,注册到同provider一致的注册中心即可发起远程调用 =======================================");
        System.out.println("======================================= 利用feign的继承特性,抽取consumer和provider的公共接口(Api模块),便于维护管理,但是也增加了两端的耦合度 =======================================");
        System.out.println("======================================= feign默认包含hystrix的依赖,如果要使用服务降级,需要yml开启配置 =======================================");
        SpringApplication.run(FeignConsumerApplication.class);
    }
}
