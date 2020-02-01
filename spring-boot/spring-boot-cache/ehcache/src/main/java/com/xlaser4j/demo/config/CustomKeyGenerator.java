package com.xlaser4j.demo.config;

import java.lang.reflect.Method;

import com.xlaser4j.demo.entity.UserEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;

/**
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/2/1 19:55
 * @description: 自定义key
 * @modified: Elijah.D
 */
@Configuration
public class CustomKeyGenerator implements KeyGenerator {
    /**
     * Default is "", meaning all method parameters are considered as a key,
     * unless a custom keyGenerator has been configured.
     * Spring Expression Language (SpEL) expression for computing the key dynamically.
     * <p>
     * 通过{@link Cacheable#keyGenerator()}自定义key,通过对象,方法,参数等信息自定义构造key
     * <p>
     * 默认key值是:方法的所有参数{@link Cacheable#key()},可以通过SpEL修改key的属性如:"#name"
     *
     * @param o
     * @param method
     * @param objects
     * @return
     */
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        return ((UserEntity)objects[0]).getId();
    }
}
