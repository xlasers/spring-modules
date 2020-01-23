package com.xlaser4j.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configure how long in seconds the response from a pre-flight request can be cached by clients.
 * {@link org.springframework.web.servlet.config.annotation.CorsRegistration#maxAge(long)}
 * <p>
 * By default this is set to 1800 seconds (30 minutes).
 *
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/1/23 22:24
 * @description:
 * @modified: Elijah.D
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    /**
     * maxAge:设置探测请求的有效时间,默认1800s.
     * <p>
     * eg:PUT请求发起前,浏览器会先发出一个探测请求,探测服务端是否支持PUT请求.
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/config/**")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowedOrigins(("http://localhost:8081"))
                .maxAge(1);
    }
}
