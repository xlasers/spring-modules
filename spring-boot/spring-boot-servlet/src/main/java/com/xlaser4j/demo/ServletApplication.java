package com.xlaser4j.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/0/0 00:00
 * @description: Start App Template
 * @modified: Elijah.D
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "com.xlaser4j.demo")
public class ServletApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================ServletComponentScan开启扫描Web三大基础组件:servlet,listener,filter==============================\n");
        SpringApplication.run(ServletApplication.class, args);
    }
}