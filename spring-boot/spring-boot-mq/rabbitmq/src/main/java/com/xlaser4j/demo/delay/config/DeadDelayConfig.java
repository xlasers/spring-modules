package com.xlaser4j.demo.delay.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @package: com.xlaser4j.demo.dead.config
 * @author: Elijah.D
 * @time: 2020/3/12 9:43
 * @description: 通过过期时间实现队列延迟消费
 * @modified: Elijah.D
 */
@Configuration
public class DeadDelayConfig {
    public static final String TTL_EXCHANGE = "ttl.exchange";

    public static final String TTL_QUEUE_A = "ttl.queueA";

    public static final String TTL_QUEUE_B = "ttl.queueB";

    public static final String TTL_QUEUE_C = "ttl.queueC";

    public static final String DEAD_DELAY_EXCHANGE = "dead.delay.exchange";

    public static final String DEAD_DELAY_QUEUE_A = "dead.delay.queueA";

    public static final String DEAD_DELAY_QUEUE_B = "dead.delay.queueB";

    public static final String DEAD_DELAY_QUEUE_C = "dead.delay.queueC";

    public static final String QUEUE_A_ROUTING_KEY = "queueA.key";

    public static final String QUEUE_B_ROUTING_KEY = "queueB.key";

    public static final String QUEUE_C_ROUTING_KEY = "queueC.key";

    /**
     * 声明通过ttl的实现的延时队列交换机
     * <p>
     * 方案1.设置不同队列的TTL属性,也即是消息的成为死信的时间,消费端只需要监听死信即可达到延迟消费的目的,但是弊端
     * 是不够灵活,一个队列只能应对一个延迟时间,如果延迟的需求很多,就需要创建不同的队列:
     * {@link this#ttlQueueA()}30s后过期, {@link this#ttlQueueB()}60s后过期
     * <p>
     * 方案2.设置消息的TTL属性,对应不同的延迟时间,不需要统一设置队列,但是因为队列有先后,如果延迟一个小时的消息在前,
     * 延迟一分钟的消息在后,这个一分钟要消费的消息仍然要等待一个小时后才会被消费.
     * {@link this#ttlQueueC()}自定义ttl时间
     * <p>
     * 方案3.设置消息的TTL属性,虽然解决了减少创建不同延迟时间的队列,但是消息不能及时成为死信,如果业务有固定的延迟
     * 可以参考使用,但是如果对于延迟时间过多,就需要通用的延迟消费支持,而插件:rabbitmq_delayed_message_exchange
     * 可以的delayTime保证延迟的消息及时变成死信保证及时消费.
     * {@link DelayConfig()}自定义延迟时间
     *
     * @return exchange
     */
    @Bean
    DirectExchange ttlExchange() {
        return new DirectExchange(TTL_EXCHANGE);
    }

    /**
     * 死信交换机
     * <p>
     * 一般用在较为重要的业务队列中,确保未被正确消费的消息不被丢弃,一般发生消费异常可能原因主要有由于消息信息本身
     * 存在错误导致处理异常,处理过程中参数校验异常,或者因网络波动导致的查询异常等等,当发生异常时,可以通过日志来获取
     * 原消息,然后让运维帮忙重新投递消息,但是麻烦且效率低下;建议通过配置死信队列,可以让未正确处理的消息暂存到另一个
     * 队列中,后续排查问题,编写对应的处理代码来处理死信消息
     * <p>
     * 成为死信的条件:
     * 1.消息被否定确认,使用channel.basicNack或channel.basicReject,并且此时requeue属性被设置为false
     * 2.消息在队列的存活时间超过设置的TTL时间
     * 3.消息队列的消息数量已经超过最大队列长度
     *
     * @return exchange
     */
    @Bean
    DirectExchange deadDelayExchange() {
        return new DirectExchange(DEAD_DELAY_EXCHANGE);
    }

    /**
     * 队列A: ttl-30s
     *
     * @return queue
     */
    @Bean
    Queue ttlQueueA() {
        Map<String, Object> deadArgs = new HashMap<>(3);

        // 业务队列绑定死信交换机和死信的routing-key
        deadArgs.put("x-dead-letter-exchange", DEAD_DELAY_EXCHANGE);

        // 声明队列的TTL,也即是消息超时会成为死信
        deadArgs.put("x-message-ttl", 30000);

        // 设置banging死信的routing-key,如果不设置,则默认是业务消息的routing-key
        /// deadArgs.put("x-dead-letter-routing-key", QUEUE_A_ROUTING_KEY);

        return QueueBuilder.durable(TTL_QUEUE_A).withArguments(deadArgs).build();
    }

    @Bean
    Binding bindingTtlA() {
        return BindingBuilder.bind(ttlQueueA()).to(ttlExchange()).with(QUEUE_A_ROUTING_KEY);
    }

    /**
     * 队列B: ttl-60s
     *
     * @return queue
     */
    @Bean
    Queue ttlQueueB() {
        Map<String, Object> deadArgs = new HashMap<>(3);
        deadArgs.put("x-dead-letter-exchange", DEAD_DELAY_EXCHANGE);
        deadArgs.put("x-message-ttl", 60000);

        return QueueBuilder.durable(TTL_QUEUE_B).withArguments(deadArgs).build();
    }

    @Bean
    Binding bindingTtlB() {
        return BindingBuilder.bind(ttlQueueB()).to(ttlExchange()).with(QUEUE_B_ROUTING_KEY);
    }

    /**
     * 队列C: 不设置队列ttl,设置消息的ttl
     *
     * @return queue
     */
    @Bean
    Queue ttlQueueC() {
        Map<String, Object> deadArgs = new HashMap<>(3);
        deadArgs.put("x-dead-letter-exchange", DEAD_DELAY_EXCHANGE);

        return QueueBuilder.durable(TTL_QUEUE_C).withArguments(deadArgs).build();
    }

    @Bean
    Binding bindingTtlC() {
        return BindingBuilder.bind(ttlQueueC()).to(ttlExchange()).with(QUEUE_C_ROUTING_KEY);
    }

    /**
     * 死信队列A
     *
     * @return queue
     */
    @Bean
    Queue deadDelayQueueA() {
        return new Queue(DEAD_DELAY_QUEUE_A);
    }

    @Bean
    Binding bindingDeadDelayA() {
        return BindingBuilder.bind(deadDelayQueueA()).to(deadDelayExchange()).with(QUEUE_A_ROUTING_KEY);
    }

    /**
     * 死信队列B
     *
     * @return queue
     */
    @Bean
    Queue deadDelayQueueB() {
        return new Queue(DEAD_DELAY_QUEUE_B);
    }

    @Bean
    Binding bindingDeadDelayB() {
        return BindingBuilder.bind(deadDelayQueueB()).to(deadDelayExchange()).with(QUEUE_B_ROUTING_KEY);
    }

    /**
     * 死信队列C
     *
     * @return queue
     */
    @Bean
    Queue deadDelayQueueC() {
        return new Queue(DEAD_DELAY_QUEUE_C);
    }

    @Bean
    Binding bindingDeadDelayC() {
        return BindingBuilder.bind(deadDelayQueueC()).to(deadDelayExchange()).with(QUEUE_C_ROUTING_KEY);
    }
}
