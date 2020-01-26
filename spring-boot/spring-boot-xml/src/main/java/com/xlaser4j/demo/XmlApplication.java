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
public class XmlApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================Springboot自定义欢迎页,静态路径下的index,然后是动态('/index')路径下的index==============================\n");
        System.out.println("\n==============================Springboot自定义Favicon.ico,默认是静态路径下的favicon.ico,然后是类路径(resources)下的Favicon.ico==============================\n");
        SpringApplication.run(XmlApplication.class, args);
    }
}