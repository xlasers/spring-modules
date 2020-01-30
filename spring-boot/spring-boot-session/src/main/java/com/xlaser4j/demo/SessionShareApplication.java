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
public class SessionShareApplication {
    public static void main(String[] args) {
        // USING GENERATED SECURITY PASSWORD
        System.out.println("\n==============================利用security拦截验证redis-session共享,security默认登陆页面username:user,项目启动控制台打印默认pwd==============================\n");
        SpringApplication.run(SessionShareApplication.class, args);
    }
}