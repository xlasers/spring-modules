package com.xlaser4j.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/0/0 00:00
 * @description: Start App Template
 * @modified: Elijah.D
 */
@SpringBootApplication
public class ActuatorApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================actuator是spring用于监控服务的,默认提供项目的基本监控信息,如果引入其他依赖,开启了自动化配置吗,就会把相关依赖的健康信息纳入actuator.==============================\n");
        System.out.println("\n==============================actuator是通过开启endpoint暴露web接口来获取json数据,而admin模块就是对这些数据的渲染出来的可视化界面.==============================\n");
        System.out.println("\n==============================actuator相关配置参考:https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#production-ready-endpoints==============================\n");
        SpringApplication.run(ActuatorApplication.class, args);
    }
}