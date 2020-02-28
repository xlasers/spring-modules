package com.xlaser4j.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/2/7 14:06
 * @description:
 * @modified: Elijah.D
 */
@SpringBootApplication
public class WarApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================springboot项目内置了tomcat可以直接jar包运行.==============================\n");
        System.out.println("\n==============================war包-1.修改pom文件,标签<packaging>war</packaging>是war,同时tomcat依赖scope是provided,打包时排除tomcat,防止war包部署到容器时发生冲突.==============================\n");
        System.out.println("\n==============================war包-2.创建ServletInitializer继承SpringBootServletInitializer,重写configure方法.==============================\n");
        SpringApplication.run(WarApplication.class, args);
    }
}
