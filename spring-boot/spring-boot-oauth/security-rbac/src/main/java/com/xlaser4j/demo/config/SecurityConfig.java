package com.xlaser4j.demo.config;

import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xlaser4j.demo.security.CustomDecisionManager;
import com.xlaser4j.demo.security.CustomSecurityFilter;
import com.xlaser4j.demo.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 角色继承:{@link RoleHierarchy}
 * <p>
 * 密码加密:{@link BCryptPasswordEncoder}
 *
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/2/2 13:22
 * @description: 通过继承实现security的配置
 * @modified: Elijah.D
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserServiceImpl service;

    private final CustomSecurityFilter filter;

    private final CustomDecisionManager manager;

    /**
     * security处理器,注入自定义的拦截器,匹配器即可!
     *
     * @see CustomSecurityFilter
     * @see CustomDecisionManager
     */
    private ObjectPostProcessor<FilterSecurityInterceptor> processor = new ObjectPostProcessor<FilterSecurityInterceptor>() {
        @Override
        public <O extends FilterSecurityInterceptor> O postProcess(O o) {
            o.setAccessDecisionManager(manager);
            o.setSecurityMetadataSource(filter);
            return o;
        }
    };

    public SecurityConfig(UserServiceImpl service, CustomSecurityFilter filter, CustomDecisionManager manager) {
        this.service = service;
        this.filter = filter;
        this.manager = manager;
    }

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
     * 角色继承,大的角色包含小角色的权限
     * <p>
     * spring-boot2.0+(security5.0+)之后的版本继承的hierarchy之间用\n换行符,之前是空格当作分隔标记
     * 因为源码解析分割符做出了对应的改变由(空格 --> \n)的变更.
     * <p>
     * hierarchy:角色继承本质上就是将小的角色名字加入到大的角色集合里:eg admin > dev
     * admin.getAuthorities = [admin,dev]
     *
     * @return
     */
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_admin > ROLE_dev";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    /**
     * 通过数据库配置登陆授权信息{@link org.springframework.security.core.userdetails.UserDetails}
     * <p>
     * 也可以直接代码配置登陆授权信息::见security-basic模块
     * <p>
     * 也可以通过application.yml文件配置
     * <p>
     * IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
     * security5.0+之后不允许明文配置密码,按照shiro的方案可以利用加密+加盐方式处理密码,但是security中提供了更方便的类
     * {@link BCryptPasswordEncoder#encode(CharSequence)} encode方法对于即使相同的密码加密出来仍然不一样
     * 设置密码为加密之后的密码,同时注入PasswordEncoder的Bean实例为BCryptPasswordEncoder
     * <p>
     * 除了上述设置密码为encoder之后的密码,还可以直接注入一个不验证PasswordEncoder的bean实例来跳过验证{@link NoOpPasswordEncoder}
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service);
    }

    /**
     * 配置权限相关信息
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 登陆成功处理逻辑,模拟返回json数据
        AuthenticationSuccessHandler successHandler = (req, res, auth) -> {
            res.setContentType("application/json;charset=utf-8");
            PrintWriter out = res.getWriter();
            ResponseEntity<Object> response = new ResponseEntity<>(auth.getPrincipal(), HttpStatus.OK);
            out.write(new ObjectMapper().writeValueAsString(response));
            out.flush();
            out.close();
        };

        // 登陆失败处理逻辑,模拟返回json数据
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

        // 登陆逻辑:withObjectPostProcessor
        http.authorizeRequests()
                .withObjectPostProcessor(processor)
                .and()
                // 登陆相关逻辑permitAll
                .formLogin().loginProcessingUrl("/custom-login")
                // 登陆账号密码前后端交互参数key:默认username,password
                .usernameParameter("name").passwordParameter("pwd")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .and()
                // 关闭csrf,否则在postman测试任何接口都是登陆页面,即使是上面制定的登陆接口/custom-login
                .csrf().disable()
        ;
    }
}
