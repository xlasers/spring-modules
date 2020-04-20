package com.xlaser4j.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @package: com.xlaser4j.config
 * @author: Elijah.D
 * @time: 2020/4/20 20:43
 * @description:
 * @modified: Elijah.D
 */
@SpringBootApplication
public class ConfigClientApplication {
    public static void main(String[] args) {
        System.out.println("\n================================ 微服务中client从config-server中的文件夹中读取配置文件,文件夹名字就client的服务名如:config-client =================================\n");
        System.out.println("\n================================ 同时client启动可能需要读取一些关键信息,如数据库链接等,需要读取server中的配置后才能启动,所以使用bootstrap配置读取server信息 =================================\n");
        SpringApplication.run(ConfigClientApplication.class, args);
    }
}
