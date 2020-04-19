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
        System.out.println("\n================================ config是spring-cloud提供的统一管理配置文件中心,支持git/svn仓库管理访问,本质上是clone远端到本地然后访问,默认是临时文件夹如: file:/C:/xxx/Local/Temp/config-repo =================================\n");
        System.out.println("\n================================ 配置远程配置中心信息,即可通过接口访问配置信息(yml/prop两种都可以),访问路径有多种如下:application表示文件名,profile表示激活的文件,label可选分支名 =================================\n");
        System.out.println("\n================================ /{application}/{profile}/[{label}]或/[{label}]/{application}-{profile}.yml或/[{label}]/{application}-{profile}.properties =================================\n");
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
