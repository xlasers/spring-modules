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
public class ThymeleafApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================Thymeleaf动态模板,默认路径templates下,使用controller跳转返回视图名称==============================\n");
        System.out.println("\n==============================Thymeleaf动态模板,如果不需要解析额外服务数据,仅仅是静态页面,但是在templates下,可以通过path路径映射完成跳转==============================\n");
        SpringApplication.run(ThymeleafApplication.class, args);
    }
}