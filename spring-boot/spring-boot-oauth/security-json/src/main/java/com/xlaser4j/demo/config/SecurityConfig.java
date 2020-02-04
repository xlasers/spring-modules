package com.xlaser4j.demo.config;

import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xlaser4j.demo.filter.CustomUsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/2/4 17:11
 * @description:
 * @modified: Elijah.D
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 登陆成功处理逻辑,模拟返回json数据
     */
    AuthenticationSuccessHandler successHandler = (req, res, auth) -> {
        res.setContentType("application/json;charset=utf-8");
        PrintWriter out = res.getWriter();
        ResponseEntity<Object> response = new ResponseEntity<>(auth.getPrincipal(), HttpStatus.OK);
        out.write(new ObjectMapper().writeValueAsString(response));
        out.flush();
        out.close();
    };

    /**
     * 注入filter,configure中的其他相关逻辑,可以在这里自定义配置
     *
     * @return
     * @throws Exception
     */
    @Bean
    CustomUsernamePasswordAuthenticationFilter customFilter() throws Exception {
        CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());

        // 自定义其他相关逻辑,在这里处理
        filter.setAuthenticationSuccessHandler(successHandler);

        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .csrf().disable();

        // 启用filter
        http.addFilterAt(customFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
