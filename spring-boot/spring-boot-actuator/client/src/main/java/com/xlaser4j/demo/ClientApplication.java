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
public class ClientApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================client就是一个绑定admin的服务,会把自己的actuator信息发送给admin用于监控.==============================\n");
        System.out.println("\n==============================关于actuator的信息暴露继承,参考actuator模块,同时client模块务必引入actuator依赖.==============================\n");
        SpringApplication.run(ClientApplication.class, args);
    }
}