package com.xlaser4j.demo.basic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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
public class TopicExchangeConfig {
    public static final String EXCHANGE_NAME = "topic";

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
    TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME, true, false);
    }

    /**
     * 创建一个queue: english
     *
     * @return queue
     */
    @Bean
    Queue english() {
        return new Queue("english");
    }

    /**
     * 创建一个queue: chinese
     *
     * @return queue
     */
    @Bean
    Queue chinese() {
        return new Queue("chinese");
    }

    /**
     * 创建一个queue: language
     *
     * @return queue
     */
    @Bean
    Queue language() {
        return new Queue("language");
    }

    /**
     * 绑定queue到exchange上,topic模式下需要routingKey统配符匹配控制
     * <p>
     * 当生产者分发消息时,当指定的routingKey以english结尾,就会分发到这个queue中
     *
     * @return binding
     */
    @Bean
    Binding bindingEnglish() {
        return BindingBuilder.bind(english()).to(topicExchange()).with("#.english");
    }

    /**
     * 绑定queue到exchange上,topic模式下需要routingKey统配符匹配控制
     * <p>
     * 当生产者分发消息时,当指定的routingKey以chinese结尾,就会分发到这个queue中
     *
     * @return binding
     */
    @Bean
    Binding bindingChinese() {
        return BindingBuilder.bind(chinese()).to(topicExchange()).with("chinese.#");
    }

    /**
     * 绑定queue到exchange上,topic模式下需要routingKey统配符匹配控制
     * <p>
     * 当生产者分发消息时,当指定的routingKey以language结尾,就会分发到这个queue中
     *
     * @return binding
     */
    @Bean
    Binding bindingLanguage() {
        return BindingBuilder.bind(language()).to(topicExchange()).with("#.language.#");
    }
}
