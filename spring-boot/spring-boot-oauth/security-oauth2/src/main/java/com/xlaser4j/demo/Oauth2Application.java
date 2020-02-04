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
public class Oauth2Application {
    public static void main(String[] args) {
        System.out.println("\n==============================oauth是一种用于第三方登陆的协议,通过提供token的方式,来访问用户的资源信息.==============================\n");
        System.out.println("\n==============================spring-oauth2是基于security来做的,主要是配置:授权服务器,资源服务器信息.==============================\n");
        SpringApplication.run(Oauth2Application.class, args);
    }
}