package com.xlaser4j.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/2/5 19:29
 * @description:
 * @modified: Elijah.D
 */
@SuppressWarnings("JavadocReference")
@Configuration
public class FanoutExchangeConfig {
    public final static String EXCHANGE_NAME = "fanout";

    /**
     * Construct a new Exchange, given a name, durability flag, auto-delete flag.
     * <p>
     * durable true if we are declaring a durable exchange (the exchange will survive a server restart)
     * {@link org.springframework.amqp.core.AbstractExchange#durable}重新启动之后,消息是否继续使用
     * <p>
     * autoDelete true if the server should delete the exchange when it is no longer in use
     * {@link org.springframework.amqp.core.AbstractExchange#autoDelete}长时间未消费的消息,是否删除
     *
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME, true, false);
    }

    /**
     * 创建一个queue: fanout-queueA
     *
     * @return
     */
    @Bean
    Queue queueA() {
        return new Queue("fanout-queueA");
    }

    /**
     * 创建一个queue: fanout-queueB
     *
     * @return
     */
    @Bean
    Queue queueB() {
        return new Queue("fanout-queueB");
    }

    /**
     * 绑定queue到exchange上,fanout模式下不需要routingKey控制
     * <p>
     * 当生产者分发消息时,只需需要选择fanout-exchange,而与之绑定的queue会自动接收到消息
     *
     * @return
     */
    @Bean
    Binding bindingA() {
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }

    /**
     * 绑定queue到exchange上,fanout模式下不需要routingKey控制
     * <p>
     * 当生产者分发消息时,只需需要选择fanout-exchange,而与之绑定的queue会自动接收到消息
     *
     * @return
     */
    @Bean
    Binding bindingB() {
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }
}
