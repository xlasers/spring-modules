package com.xlaser4j.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/0/0 00:00
 * @description: Start App Template
 * @modified: Elijah.D
 */
@EnableScheduling
@SpringBootApplication
public class QuartzApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================SpringBoot定时器支持注解@Schedule和Quartz两种实现方式,前者简单易用,后者功能多灵活通用==============================\n");
        SpringApplication.run(QuartzApplication.class, args);
    }
}