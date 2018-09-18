package com.xlasers.spring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * The type Redis config.
 *
 * @package: com.xlasers.spring.config
 * @author: Elijah.D
 * @time: CreateAt 2018/9/28 && 9:50
 * @description: 获取redisTemplate
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Configuration
@SuppressWarnings("unchecked")
public class RedisConfig {
    /**
     * Gets redis template.
     *
     * <p>1.RedisTemplate对象默认使用jdkSerializer实现序列化,利用json更换序列化的实现方式
     *
     * <p>2.StringRedisTemplate同样可以解决序列化问题
     *
     * @param factory the factory
     * @return the redis template
     * @see Jackson2JsonRedisSerializer
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        //json实现k-v的序列化
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        serializer.setObjectMapper(new ObjectMapper());

        template.setKeySerializer(serializer);
        template.setValueSerializer(serializer);

        return template;
    }
}
