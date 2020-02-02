package com.xlaser4j.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/2/2 16:18
 * @description: 如果权限粒度更细, 可通过不同的内部类实现细粒度权限配置
 * @modified: Elijah.D
 */
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MultiSecurityConfig {
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("$2a$10$tgm9/1L1C5rby2OfUoOlu.PiTe0nZcJJpNPNRPkEylkVALZdMyNSm").roles("admin")
                .and()
                .withUser("elijah").password("$2a$10$zQYRGu6uAelTp56CQdwXa.cn7t4ROrbG/fpiiEBqmzdCNO04boQl.").roles("user");
    }

    @Configuration
    @Order(1)
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            System.out.println("用户账号信息公用,这里自定义配置admin权限细节: 例如(order=1),优先拦截所有a/请求,需要admin角色!");
            http.antMatcher("/a/**").authorizeRequests().anyRequest().hasAnyRole("admin");
        }
    }

    @Configuration
    @Order(2)
    public static class UserSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            System.out.println("用户账号信息公用,这里自定义配置user权限细节: 然后(order=2),admin过滤完成后,再使用这个二级的拦截验证等控制细节!");
            http.authorizeRequests().anyRequest().authenticated();
        }
    }
}
