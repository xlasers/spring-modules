package com.xlaser4j.consul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @package: com.xlaser4j.consul.config
 * @author: Elijah.D
 * @time: 2020/3/22 15:54
 * @description: Spring提供的RestTemplate
 * @modified: Elijah.D
 */
@Configuration
public class RestTemplateConfig {
    /**
     * 正常的restTemplate,调用路径必须准确可访问: http://localhost:8082/ep
     *
     * @return
     */
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
