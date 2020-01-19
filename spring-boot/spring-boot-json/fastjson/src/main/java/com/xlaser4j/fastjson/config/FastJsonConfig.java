package com.xlaser4j.fastjson.config;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

/**
 * @package: com.xlaser4j.fastjson.config
 * @author: Elijah.D
 * @time: 2020/1/19 15:29
 * @description: 配置Jackson
 * @modified: Elijah.D
 */
@Configuration
public class FastJsonConfig {
    /**
     * springboot中没有自动化配置fastJson,需要手动实现convert等相关json的序列化工作
     *
     * @return converter
     */
    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {

        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        com.alibaba.fastjson.support.config.FastJsonConfig config = new com.alibaba.fastjson.support.config.FastJsonConfig();
        config.setDateFormat("yyyy-MMM-dd");

        // 中文乱码
        List<MediaType> types = new ArrayList<>();
        types.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(types);

        converter.setFastJsonConfig(config);
        return converter;
    }
}
