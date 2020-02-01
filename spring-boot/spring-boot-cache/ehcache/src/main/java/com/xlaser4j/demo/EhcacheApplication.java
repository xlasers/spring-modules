package com.xlaser4j.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/0/0 00:00
 * @description: Start App Template
 * @modified: Elijah.D
 */
@EnableCaching
@SpringBootApplication
public class EhcacheApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================spring-boot-starter-cache制定了缓存使用的规范,无论是ehcache还是redis都要实现这个规范,反映到代码就是使用相同的注解,即使缓存的类型换了,注解还是不变的.==============================\n");
        System.out.println("\n==============================类比jdbc规范无论数据库是哪种,只要使用jdbc就无需改动代码,因为数据库驱动都实现了这个规范,因此对外的api都是一致的.==============================\n");
        SpringApplication.run(EhcacheApplication.class, args);
    }
}