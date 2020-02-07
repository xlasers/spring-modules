package com.xlaser4j.demo;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/0/0 00:00
 * @description: Start App Template
 * @modified: Elijah.D
 */
@EnableAdminServer
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================admin(@EnableAdminServer)是一个可视化监控页面,收集绑定服务暴露的actuator信息,渲染成web界面.==============================\n");
        System.out.println("\n==============================client是绑定admin的服务,会把自己的actuator信息发送给admin用于监控.==============================\n");
        SpringApplication.run(AdminApplication.class, args);
    }
}