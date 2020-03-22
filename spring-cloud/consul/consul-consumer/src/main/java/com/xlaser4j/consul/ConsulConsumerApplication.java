package com.xlaser4j.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @package: com.xlaser4j.consul
 * @author: Elijah.D
 * @time: 2020/3/22 20:14
 * @description: 官网https://www.consul.io/
 * @modified: Elijah.D
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulConsumerApplication {
    public static void main(String[] args) {
        System.out.println("\n================================ 服务注册与发现,除了Eureka之外还有Zookeeper,Consul等开源替代方案,尤其是eureka闭源之后 =================================\n");
        System.out.println("\n================================ Eureka本身就是一个微服务server,而Zookeeper需要安装对应的server,通常用于dubbo;Consul(go语言)也需要安装对应的server,通常用于service mesh =================================\n");
        System.out.println("\n================================ Eureka默认@EnableDiscoveryClient,Consul需要手动开启;同时安装启动Consul默认端口:8500,通过浏览器查看web监控页面 =================================\n");
        SpringApplication.run(ConsulConsumerApplication.class, args);
    }
}
