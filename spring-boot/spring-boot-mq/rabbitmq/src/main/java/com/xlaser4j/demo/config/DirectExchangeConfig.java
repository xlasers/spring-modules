package com.xlaser4j.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
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
public class DirectExchangeConfig {
    public final static String EXCHANGE_NAME = "direct";

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
    DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME, true, false);
    }

    /**
     * 创建一个queue: direct-queue
     * <p>
     * direct模式下,无需配置directExchange和binding,默认就会设置,这里只是为了演示四种配置而配置
     *
     * @return
     */
    @Bean
    Queue queue() {
        return new Queue("direct-queue");
    }

    /**
     * 绑定queue到exchange上,指定一个routingKey(direct模式下这里key无用??)
     * <p>
     * 当生产者分发消息时,需要指定一个routingKey,只要queue的name等于生产消息的key就可以接收到消息
     *
     * @return
     */
    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with("anyKey");
    }
}
