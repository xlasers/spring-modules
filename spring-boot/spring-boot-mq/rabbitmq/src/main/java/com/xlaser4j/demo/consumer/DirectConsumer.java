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
public class DirectConsumer {
    /**
     * 订阅direct-queue
     *
     * @param msg
     */
    @RabbitListener(queues = "direct-queue")
    public void direct(String msg) {
        System.out.println("direct-queue-消费消息:" + msg);
    }
}
