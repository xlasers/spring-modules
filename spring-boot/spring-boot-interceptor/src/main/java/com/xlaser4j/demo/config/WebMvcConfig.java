package com.xlaser4j.demo.config;

import com.xlaser4j.demo.interceptor.CustomInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/1/22 22:01
 * @description:
 * @modified: Elijah.D
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //@Autowired
    //CustomInterceptor customInterceptor;

    @Bean
    CustomInterceptor customInterceptor() {
        return new CustomInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor()).addPathPatterns("/");
    }
}
