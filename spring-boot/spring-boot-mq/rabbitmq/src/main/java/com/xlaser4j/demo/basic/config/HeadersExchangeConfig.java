package com.xlaser4j.demo.basic.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @package: com.xlaser4j.demo.basic.config
 * @author: Elijah.D
 * @time: 2020/2/5 19:29
 * @description:
 * @modified: Elijah.D
 */
@SuppressWarnings("JavadocReference")
@Configuration
public class HeadersExchangeConfig {
    public static final String EXCHANGE_NAME = "headers";

    /**
     * Construct a new Exchange, given a name, durability flag, auto-delete flag.
     * <p>
     * durable true if we are declaring a durable exchange (the exchange will survive a server restart)
     * {@link org.springframework.amqp.core.AbstractExchange#durable}重新启动之后,消息是否继续使用
     * <p>
     * autoDelete true if the server should delete the exchange when it is no longer in use
     * {@link org.springframework.amqp.core.AbstractExchange#autoDelete}长时间未消费的消息,是否删除
     *
     * @return exchange
     */
    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange(EXCHANGE_NAME, true, false);
    }

    /**
     * 创建一个queue: key
     * <p>
     * headers模式下根据匹配规则,如果headers中包含key则路由到这个queue
     * {@link MessageBuilder#setHeader(String, Object)}
     *
     * @return queue
     */
    @Bean
    Queue key() {
        return new Queue("key");
    }

    /**
     * 创建一个queue: keyValue
     * <p>
     * headers模式下根据匹配规则,如果headers中包含key,且value相同,则路由到这个queue
     * {@link MessageBuilder#setHeader(String, Object)}
     *
     * @return queue
     */
    @Bean
    Queue keyValue() {
        return new Queue("keyValue");
    }

    /**
     * 创建一个queue: allKeyValue
     * <p>
     * headers模式下根据匹配规则,如果headers中包含所有的key,且value相同,则路由到这个queue
     * {@link MessageBuilder#setHeader(String, Object)}
     *
     * @return queue
     */
    @Bean
    Queue allKeyValue() {
        return new Queue("allKeyValue");
    }

    /**
     * 绑定queue到exchange上,headers模式下不需要routingKey控制
     * <p>
     * 当生产者分发消息时,使用{@link org.springframework.amqp.core.Message}作为载体,当message的headers中
     * 包含key,就会分发到这个queue中
     *
     * @return binding
     */
    @Bean
    Binding bindingKey() {
        return BindingBuilder.bind(key()).to(headersExchange()).where("key").exists();
    }

    /**
     * 绑定queue到exchange上,topic模式下需要routingKey统配符匹配控制
     * <p>
     * 当生产者分发消息时,使用{@link org.springframework.amqp.core.Message}作为载体,当message的headers中
     * 包含key且value相同,就会分发到这个queue中
     *
     * @return binding
     */
    @Bean
    Binding bindingKeyValue() {
        Map<String, Object> map = new HashMap<>(4);
        map.put("key", "value");
        map.put("key1", "value1");
        return BindingBuilder.bind(keyValue()).to(headersExchange()).whereAny(map).match();
    }

    /**
     * 绑定queue到exchange上,topic模式下需要routingKey统配符匹配控制
     * <p>
     * 当生产者分发消息时,使用{@link org.springframework.amqp.core.Message}作为载体,当message的headers中
     * 包含所有的key且value相同,就会分发到这个queue中
     *
     * @return binding
     */
    @Bean
    Binding bindingAllKeyValue() {
        Map<String, Object> map = new HashMap<>(4);
        map.put("key1", "value1");
        map.put("key2", "value2");
        return BindingBuilder.bind(allKeyValue()).to(headersExchange()).whereAll(map).match();
    }
}
