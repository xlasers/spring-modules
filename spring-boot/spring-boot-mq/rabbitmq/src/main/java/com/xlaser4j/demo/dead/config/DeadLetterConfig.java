package com.xlaser4j.demo.dead.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @package: com.xlaser4j.demo.dead.config
 * @author: Elijah.D
 * @time: 2020/3/12 9:43
 * @description: 死信队列配置
 * @modified: Elijah.D
 */
@Configuration
public class DeadLetterConfig {
    public static final String BU_EXCHANGE = "bu.exchange";

    public static final String BU_QUEUE_A = "bu.queueA";

    public static final String BU_QUEUE_B = "bu.queueB";

    public static final String DEAD_EXCHANGE = "dead.exchange";

    public static final String DEAD_QUEUE_A = "dead.queueA";

    public static final String DEAD_QUEUE_B = "dead.queueB";

    public static final String DEAD_QUEUE_A_ROUTING_KEY = "dead.queueA.key";

    public static final String DEAD_QUEUE_B_ROUTING_KEY = "dead.queueB.key";

    /**
     * 业务交换机: 这里声明为fanout,配置两个队列,当出现死信时,统一路由到一个死信交换机
     * <p>
     * 一般来说,会为每个业务队列分配一个独有的路由key,为每个重要的业务队列配置一个死信队列进行监听,
     * 队列的交换机类型可以任意选择:Fanout,Direct,Topic
     * <p>
     * 被nck或reject的消息由RabbitMQ投递到死信交换机中
     *
     * @return exchange
     */
    @Bean
    FanoutExchange buExchange() {
        return new FanoutExchange(BU_EXCHANGE);
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
    DirectExchange deadExchange() {
        return new DirectExchange(DEAD_EXCHANGE);
    }

    /**
     * 业务队列A
     *
     * @return queue
     */
    @Bean
    Queue buQueueA() {
        Map<String, Object> deadArgs = new HashMap<>(4);

        // 业务队列绑定死信交换机和死信的routing-key
        deadArgs.put("x-dead-letter-exchange", DEAD_EXCHANGE);

        // 设置banging死信的routing-key,如果不设置,则默认是业务消息的routing-key
        deadArgs.put("x-dead-letter-routing-key", DEAD_QUEUE_A_ROUTING_KEY);

        return QueueBuilder.durable(BU_QUEUE_A).withArguments(deadArgs).build();
    }

    @Bean
    Binding bindingBuA() {
        return BindingBuilder.bind(buQueueA()).to(buExchange());
    }

    /**
     * 业务队列B
     *
     * @return queue
     */
    @Bean
    Queue buQueueB() {
        Map<String, Object> deadArgs = new HashMap<>(4);
        deadArgs.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        deadArgs.put("x-dead-letter-routing-key", DEAD_QUEUE_B_ROUTING_KEY);

        return QueueBuilder.durable(BU_QUEUE_B).withArguments(deadArgs).build();
    }

    @Bean
    Binding bindingBuB() {
        return BindingBuilder.bind(buQueueB()).to(buExchange());
    }

    /**
     * 死信队列A
     *
     * @return queue
     */
    @Bean
    Queue deadQueueA() {
        return new Queue(DEAD_QUEUE_A);
    }

    @Bean
    Binding bindingDeadA() {
        return BindingBuilder.bind(deadQueueA()).to(deadExchange()).with(DEAD_QUEUE_A_ROUTING_KEY);
    }

    /**
     * 死信队列B
     *
     * @return queue
     */
    @Bean
    Queue deadQueueB() {
        return new Queue(DEAD_QUEUE_B);
    }

    @Bean
    Binding bindingDeadB() {
        return BindingBuilder.bind(deadQueueB()).to(deadExchange()).with(DEAD_QUEUE_B_ROUTING_KEY);
    }
}
