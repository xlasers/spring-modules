package com.xlaser4j.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @package: com.xlaser4j.demo.consumer
 * @author: Elijah.D
 * @time: 2020/2/5 21:25
 * @description:
 * @modified: Elijah.D
 */
@Component
public class FanoutConsumer {
    /**
     * 订阅fanout-queueA
     *
     * @param msg
     */
    @RabbitListener(queues = "fanout-queueA")
    public void fanoutA(String msg) {
        System.out.println("fanout-queueA-消费消息: " + msg);
    }

    /**
     * 订阅fanout-queueB
     *
     * @param msg
     */
    @RabbitListener(queues = "fanout-queueB")
    public void fanoutB(String msg) {
        System.out.println("fanout-queueB-消费消息: " + msg);
    }
}
