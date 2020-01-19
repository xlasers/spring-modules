package com.xlaser4j.jackson.config;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * 不定义convert,springboot中自动化配置也会序列化返回json字符串,这里只是演示通过自定义实现日期格式化,及其序列化内部是实现
 *
 * @package: com.xlaser4j.jackson.config
 * @author: Elijah.D
 * @time: 2020/1/19 15:29
 * @description: 配置Jackson
 * @modified: Elijah.D
 */
@Configuration
@SuppressWarnings("JavadocReference")
public class JacksonConfig {
    /**
     * {@link org.springframework.boot.autoconfigure.http.JacksonHttpMessageConvertersConfiguration}
     * ConditionalOnMissingBean(value = MappingJackson2HttpMessageConverter.class)配置中如果没有这个
     * converter就会默认提供,如下是手动配置,可以实现一些自定义功能.
     * <p>
     * 也可以手动定义一个{@link com.fasterxml.jackson.databind.ObjectMapper},因为Mapper是自动注入的,默认是用
     * 依赖中的mapper实现convert@ConditionalOnClass(ObjectMapper.class)的相关功能(但是没有converter功能多)
     * {@link org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration.JacksonObjectMapperConfiguration}
     *
     * @return converter
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MMM-DD"));
        converter.setObjectMapper(mapper);
        return converter;
    }

    //@Bean
    //public ObjectMapper objectMapper(){}
}
