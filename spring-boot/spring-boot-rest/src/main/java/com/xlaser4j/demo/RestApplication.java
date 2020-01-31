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
public class RestApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================通过spring-boot-starter-data-rest自动构建restful服务,不需要controller默认提供对应的http增删改查方法,同时可以支持自定义方法==============================\n");
        System.out.println("\n==============================1.通过restTemplate测试,直接获取body信息,详情见测试类RestTest==============================\n");
        System.out.println("\n==============================2.通过postman等工具,除了body信息,还有额外详细的描述信息,自行测试==============================\n");
        SpringApplication.run(RestApplication.class, args);
    }
}