package com.xlaser4j.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 执行逻辑是post方法,去参数方式request中get对应的key: username, password.
 * {@link org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#attemptAuthentication(HttpServletRequest, HttpServletResponse)}
 * <p>
 * 重写这个filter,然后在security中配置这个filter即可
 *
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/0/0 00:00
 * @description: Start App Template
 * @modified: Elijah.D
 */
@SpringBootApplication
public class SecurityJsonApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================在ssm项目中shiro是轻量级权限控制框架,配置相比security简单很多;但是spring-boot-starter-security大大简化了security配置,方便易用,让这个重量级权限框架得以流行,本身security功能比shiro就多,只是因为之前配置繁琐难用,普及度不高.==============================\n");
        System.out.println("\n==============================json是学习security怎么修改登陆参数为application/json形式,默认是key-value.==============================\n");
        SpringApplication.run(SecurityJsonApplication.class, args);
    }
}