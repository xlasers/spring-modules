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
public class SecurityBasicApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================在ssm项目中shiro是轻量级权限控制框架,配置相比security简单很多;但是spring-boot-starter-security大大简化了security配置,方便易用,让这个重量级权限框架得以流行,本身security功能比shiro就多,只是因为之前配置繁琐难用,普及度不高.==============================\n");
        System.out.println("\n==============================basic是学习security配置相关基础,暂时不涉及数据库,更多请查看security-db相关模块.==============================\n");
        SpringApplication.run(SecurityBasicApplication.class, args);
    }
}