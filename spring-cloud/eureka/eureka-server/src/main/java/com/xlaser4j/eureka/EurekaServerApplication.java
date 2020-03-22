package com.xlaser4j.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @package: com.xlaser4j.eureka
 * @author: Elijah.D
 * @time: 2020/3/21 20:14
 * @description:
 * @modified: Elijah.D
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {
    public static void main(String[] args) {
        System.out.println("\n================================ Eureka是Netflix开源的服务发现注册组件,但是2018年闭源停更,老项目可能仍在使用,这里简单了解使用 =================================\n");
        System.out.println("\n================================ 注册中心是核心组件,这里通过修改本地hosts文件,创建了域名eurekaA,eurekaB,通过jar包启动模拟集群环境 =================================\n");
        System.out.println("\n================================ java -jar eureka-0.0.1-SNAPSHOT.jar --spring.profiles.active=eurekaA =================================\n");
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
