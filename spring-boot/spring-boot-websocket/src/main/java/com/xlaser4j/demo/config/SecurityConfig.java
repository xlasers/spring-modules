package com.xlaser4j.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/2/2 13:22
 * @description: 通过继承实现security的配置
 * @modified: Elijah.D
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * security5.0+之后明文密码异常问题:
     * IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
     * <p>
     * {@code  NoOpPasswordEncoder.getInstance()}
     * 测试过程中,设置密码为明文,需要注入这个NoPassword的Bean来跳过验证(这是个废弃方法,仅仅在测试环境使用)
     * <p>
     * BCryptPasswordEncoder:Encoded password does not look like BCrypt
     * 正确注入BCryptPasswordEncoder并且设置密码为encode加密的密码,否则密码不正确,控制台
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {

        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 硬编码:配置登陆授权信息
     * <p>
     * 也可以通过application.yml文件配置
     * <p>
     * 也可以通过数据库配置:见security-db模块
     * <p>
     * IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
     * security5.0+之后不允许明文配置密码,按照shiro的方案可以利用加密+加盐方式处理密码,但是security中提供了更方便的类
     * {@link org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder#encode(CharSequence)}
     * encode方法对于即使相同的密码加密出来仍然不一样,设置密码为加密之后的密码,注入PasswordEncoder#BCryptPasswordEncoder
     * <p>
     * 除了上述设置密码为encoder之后的密码,还可以直接注入一个不验证PasswordEncoder的bean实例来跳过验证{@link NoOpPasswordEncoder}
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("1234").roles("admin")
                .and()
                .withUser("elijah").password("1234").roles("user");
    }

    /**
     * 配置权限相关信息
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and().formLogin();
    }
}
