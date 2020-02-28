package com.xlaser4j.demo;

import javax.annotation.PostConstruct;

import com.xlaser4j.demo.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2019/12/18 16:36
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@SpringBootApplication
public class YmlApplication {
    @Autowired
    Company company;

    public static void main(String[] args) {
        SpringApplication.run(YmlApplication.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println("\n============================================================测试yml使用============================================================");
        System.out.println("yml使用要注意顺序格式,yml格式简单方便,但是目前不支持自定义yml的注解注入使用,只能使用application.yml/yaml");
        System.out.println(company);
        System.out.println("yml使用要注意顺序格式,yml格式简单方便,但是目前不支持自定义yml的注解注入使用,只能使用application.yml/yaml");
        System.out.println("============================================================测试yml使用============================================================\n");
    }
}
