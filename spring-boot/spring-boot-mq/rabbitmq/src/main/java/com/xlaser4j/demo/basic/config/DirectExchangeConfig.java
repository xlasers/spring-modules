package com.xlaser4j.demo.basic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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
public class DirectExchangeConfig {
    public static final String EXCHANGE_NAME = "direct";

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
    DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME, true, false);
    }

    /**
     * 创建一个queue: direct-queue
     * <p>
     * direct模式下,无需配置directExchange和binding,默认就会设置,这里只是为了演示四种配置而配置
     *
     * @return queue
     */
    @Bean
    Queue queue() {
        return new Queue("direct-queue");
    }

    /**
     * 绑定queue到exchange上,指定一个routingKey
     *
     * @return binding
     */
    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with("direct-key");
    }
}
