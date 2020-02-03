package com.xlaser4j.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 拦截url,从数据库获取url对应的role
 * {@link com.xlaser4j.demo.security.CustomSecurityFilter}
 * <p>
 * 判断user,是否有足够的role覆盖到上述的role
 * {@link com.xlaser4j.demo.security.CustomDecisionManager}
 * <p>
 * 真正触发执行上述核心逻辑的config
 * {@link com.xlaser4j.demo.config.SecurityConfig#processor}
 *
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/0/0 00:00
 * @description: Start App Template
 * @modified: Elijah.D
 */
@SuppressWarnings("JavadocReference")
@SpringBootApplication
public class SecurityRbacApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================在ssm项目中shiro是轻量级权限控制框架,配置相比security简单很多;但是spring-boot-starter-security大大简化了security配置,方便易用,让这个重量级权限框架得以流行,本身security功能比shiro就多,只是因为之前配置繁琐难用,普及度不高.==============================\n");
        System.out.println("\n==============================rbac是学习security利用数据库存储权限相关信息,基于rbac通过数据库动态配置权限.==============================\n");
        System.out.println("\n==============================通过实现security:FilterInvocationSecurityMetadataSource,AccessDecisionManager两个核心类实现权限的对比控制.==============================\n");
        SpringApplication.run(SecurityRbacApplication.class, args);
    }
}