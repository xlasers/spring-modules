package com.xlaser4j.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

/**
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/1/31 21:01
 * @description: rest 配置类
 * @modified: Elijah.D
 * @see RepositoryRestConfiguration
 */
@Configuration
public class RestConfig implements RepositoryRestConfigurer {
    /**
     * 配置rest基础信息,同样可以在application.yml中配置
     *
     * @param config
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config
                .setBasePath("/api/v1")
                .setReturnBodyOnCreate(true);
    }
}
