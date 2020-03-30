package com.xlaser4j.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @package: com.xlaser4j.zuul
 * @author: Elijah.D
 * @time: 2020/3/30 21:08
 * @description:
 * @modified: Elijah.D
 */
@EnableZuulProxy
@SpringBootApplication
public class ZuulProxyApplication {
    public static void main(String[] args) {
        System.out.println("============================ Zuul用于微服务的网关代理,服务间的路由,统一认证管理等处理,但是由于Netflix闭源问题,现在主流方案是Gateway ============================");
        System.out.println("============================ 使用注解:EnableZuulProxy开启网关代理 ============================");
        System.out.println("============================ Zuul通过过滤器实现权限控制,监控,动态路由,负载均衡,静态资源处理等功能;过滤器类型: PRE, POST, ROUTING, ERROR, STATIC ============================");
        SpringApplication.run(ZuulProxyApplication.class);
    }
}
