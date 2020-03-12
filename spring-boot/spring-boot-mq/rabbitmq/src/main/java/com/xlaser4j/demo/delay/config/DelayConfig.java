package com.xlaser4j.demo.delay.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @package: com.xlaser4j.demo.dead.config
 * @author: Elijah.D
 * @time: 2020/3/12 9:43
 * @description: 通过延时插件实现队列的延迟消费
 * @modified: Elijah.D
 */
@Configuration
public class DelayConfig {
    public static final String DELAY_EXCHANGE = "delay.exchange";

    public static final String DELAY_QUEUE = "delay.queue";

    public static final String DELAY_QUEUE_ROUTING_KEY = "queue.key";

    /**
     * 声明延时队列交换机
     * <p>
     * 方案3.设置消息的TTL属性,虽然解决了减少创建不同延迟时间的队列,但是消息不能及时成为死信,如果业务有固定的延迟
     * 可以参考使用,但是如果对于延迟时间过多,就需要通用的延迟消费支持,而插件:rabbitmq_delayed_message_exchange
     * 可以的delayTime保证延迟的消息及时消费. {@link this#delayQueue()}自定义delayTime
     *
     * @return exchange
     */
    @Bean
    CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>(3);
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAY_EXCHANGE, "x-delayed-message", true, false, args);
    }

    /**
     * 通过插件实现延迟队列: 自定义消息的delayTime
     *
     * @return queue
     */
    @Bean
    Queue delayQueue() {
        return new Queue(DELAY_QUEUE);
    }

    @Bean
    Binding bindingDelay() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_QUEUE_ROUTING_KEY).noargs();
    }
}
