package com.xlaser4j.gson.config;

import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * 不定义convert,springboot中自动化配置也会序列化返回json字符串,这里只是演示通过自定义实现日期格式化,及其序列化内部是实现
 *
 * @package: com.xlaser4j.gson.config
 * @author: Elijah.D
 * @time: 2020/1/19 15:29
 * @description: 配置Jackson
 * @modified: Elijah.D
 */
@Configuration
@SuppressWarnings("JavadocReference")
public class GsonConfig {
    /**
     * {@link org.springframework.boot.autoconfigure.http.GsonHttpMessageConvertersConfiguration.GsonHttpMessageConverterConfiguration}
     * ConditionalOnMissingBean(value = GsonHttpMessageConverter.class)配置中如果没有这个
     * converter就会默认提供,如下是手动配置,可以实现一些自定义功能.
     * <p>
     * 也可以手动定义一个{@link com.google.gson.Gson},因为Gson是自动注入的,默认是用依赖中的gson实现convert
     * ConditionalOnBean(Gson.class)的相关功能(但是没有converter功能多)
     * {@link org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration}
     *
     * @return converter
     */
    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter() {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(new GsonBuilder().setDateFormat("yyyy-MMM-dd").create());
        return converter;
    }

    //@Bean
    //public Gson gson(){}
}
