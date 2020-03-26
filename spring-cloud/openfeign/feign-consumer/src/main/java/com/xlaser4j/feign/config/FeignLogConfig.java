package com.xlaser4j.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @package: com.xlaser4j.feign.config
 * @author: Elijah.D
 * @time: 2020/3/26 14:38
 * @description: 配置feign的日志级别
 * @modified: Elijah.D
 */
@Configuration
public class FeignLogConfig {
    /**
     * feign一共四种日志级别: {@link Logger.Level}对应不同的日志信息
     * <p>
     * 注意,需要项目的日志级别为debug;可以通过yml文件配置feign包下的类为debug
     *
     * @return level
     */
    @Bean
    Logger.Level level() {
        return Logger.Level.FULL;
    }
}
