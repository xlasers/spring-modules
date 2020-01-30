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
public class RedisApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================springboot-data-redis版本默认使用的时lettuce,注意application中的连接池配置,如果需要更换Jedis则排除lettuce引入Jedis即可.==============================\n");
        System.out.println("\n==============================redis5.0+版本需要配置conf显示设置密码???==============================\n");
        SpringApplication.run(RedisApplication.class, args);
    }
}