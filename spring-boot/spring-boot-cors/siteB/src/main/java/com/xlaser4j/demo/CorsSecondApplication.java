package com.xlaser4j.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * {@link org.springframework.web.bind.annotation.CrossOrigin}可以注解到类或者方法上.
 * <p>
 * {@link org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addCorsMappings(CorsRegistry)}
 *
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/0/0 00:00
 * @description: Start App Template
 * @modified: Elijah.D
 */
@SpringBootApplication
public class CorsSecondApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================通过注解@CorssOrigin实现局部跨域==============================\n");
        System.out.println("\n==============================通过重写addCorsMappings()实现全局跨域==============================\n");
        SpringApplication.run(CorsSecondApplication.class, args);
    }
}
