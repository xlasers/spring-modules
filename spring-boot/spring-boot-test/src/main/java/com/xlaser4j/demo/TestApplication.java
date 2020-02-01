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
public class TestApplication {
    public static void main(String[] args) {
        System.out.println("\n============================== @SpringBootTest注解默认寻找启动类,提供Context环境 ==============================\n");
        System.out.println("\n============================== 1.更多junit相关测试参考:https://github.com/xlaser4j/junit4j ==============================\n");
        SpringApplication.run(TestApplication.class, args);
    }
}