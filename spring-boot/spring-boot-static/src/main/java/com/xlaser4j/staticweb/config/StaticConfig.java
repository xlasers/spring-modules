package com.xlaser4j.staticweb.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @package: com.xlaser4j.staticweb.config
 * @author: Elijah.D
 * @time: 2020/1/19 17:52
 * @description: 测试开启注解后, custom-by-config路径才会生效
 * @modified: Elijah.D
 */
// @Configuration
public class StaticConfig implements WebMvcConfigurer {
    /**
     * 重写locations,自定义资源路径,同application.yml效果一样,注意顺序优先级
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/META-INF/resources/",
                "classpath:/resources/",
                "classpath:/static/",
                "classpath:/public/",
                "classpath:/custom-by-yml/",
                "classpath:/custom-by-config/"
        );
    }
}
