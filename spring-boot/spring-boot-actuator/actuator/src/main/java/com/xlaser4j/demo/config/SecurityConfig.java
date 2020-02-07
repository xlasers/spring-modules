package com.xlaser4j.demo.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/2/7 13:25
 * @description:
 * @modified: Elijah.D
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 配置endpoint的权限,必须admin角色访问
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeRequests().anyRequest()
                .hasRole("devOps")
                .and()
                // postman 添加basic auth即可
                .httpBasic();
    }
}
