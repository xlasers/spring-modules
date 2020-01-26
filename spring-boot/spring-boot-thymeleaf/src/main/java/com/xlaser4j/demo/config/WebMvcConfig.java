package com.xlaser4j.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/1/26 12:14
 * @description:
 * @modified: Elijah.D
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 配置路径映射,不通过controller实现动态试图的转发访问
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry
                .addViewController("/path-mapping")
                .setViewName("path-mapping");
    }
}
