package com.xlaser4j.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @package: com.xlaser4j.consul
 * @author: Elijah.D
 * @time: 2020/3/29 20:14
 * @description:
 * @modified: Elijah.D
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {
    public static void main(String[] args) {
        System.out.println("\n================================ config是spring-cloud提供的统一管理配置文件中心,支持git/svn仓库管理 =================================\n");
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
