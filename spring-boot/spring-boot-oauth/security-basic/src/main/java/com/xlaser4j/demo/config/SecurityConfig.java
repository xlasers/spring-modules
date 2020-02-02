package com.xlaser4j.demo.config;

import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/2/2 13:22
 * @description: 通过继承实现security的配置
 * @modified: Elijah.D
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
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
     * 登陆失败处理逻辑,模拟返回json数据
     * <p>
     * {@link org.springframework.security.core.AuthenticationException}
     */
    AuthenticationFailureHandler failureHandler = (req, res, e) -> {
        res.setContentType("application/json;charset=utf-8");
        PrintWriter out = res.getWriter();
        String error = "登陆失败!";
        // 自定义登陆失败细节处理
        if (e instanceof BadCredentialsException) {
            error = "账号或密码错误!";
        } else if (e instanceof LockedException) {
            error = "账号锁定!";
        }
        ResponseEntity<String> response = new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
        out.write(new ObjectMapper().writeValueAsString(response));
        out.flush();
        out.close();
    };

    /**
     * 登出逻辑
     */
    LogoutSuccessHandler logoutHandler = (req, res, auth) -> {
        res.setContentType("application/json;charset=utf-8");
        PrintWriter out = res.getWriter();
        ResponseEntity<String> response = new ResponseEntity<>("注销成功!", HttpStatus.OK);
        out.write(new ObjectMapper().writeValueAsString(response));
        out.flush();
        out.close();
    };

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

        /// return NoOpPasswordEncoder.getInstance();

        return new BCryptPasswordEncoder();
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
     * encode方法对于即使相同的密码加密出来仍然不一样,见测试{@link com.xlaser4j.demo.temp.Test#main(String[])}
     * 设置密码为加密之后的密码,同时注入PasswordEncoder的Bean实例为BCryptPasswordEncoder
     * <p>
     * 除了上述设置密码为encoder之后的密码,还可以直接注入一个不验证PasswordEncoder的bean实例来跳过验证{@link NoOpPasswordEncoder}
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("$2a$10$tgm9/1L1C5rby2OfUoOlu.PiTe0nZcJJpNPNRPkEylkVALZdMyNSm").roles("admin")
                .and()
                .withUser("elijah").password("$2a$10$zQYRGu6uAelTp56CQdwXa.cn7t4ROrbG/fpiiEBqmzdCNO04boQl.").roles("user");
    }

    /**
     * 配置权限相关信息
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // ADMIN权限
                .antMatchers("/a/**").hasRole("admin")
                // USER权限
                .antMatchers("/u/**").hasRole("user")
                .antMatchers("/au/**").hasAnyRole("admin", "user")
                // 除了a/**,u/**之外的只需要登陆即可访问
                .anyRequest().authenticated()
                .and()
                // 登陆相关逻辑permitAll
                .formLogin().loginProcessingUrl("/custom-login")
                // 登陆账号密码前后端交互参数key:默认username,password
                .usernameParameter("name").passwordParameter("pwd")
                // 前端后分离,登陆成功/失败处理逻辑用handler(这里模拟返回json数据),前后端不分离可以用forwardUrl完成路径转发
                .successHandler(successHandler)
                //.successForwardUrl()
                .failureHandler(failureHandler)
                .permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler(logoutHandler)
                .and()
                // 关闭csrf,否则在postman测试任何接口都是登陆页面,即使是上面制定的登陆接口/custom-login
                .csrf().disable()
        ;
    }
}
