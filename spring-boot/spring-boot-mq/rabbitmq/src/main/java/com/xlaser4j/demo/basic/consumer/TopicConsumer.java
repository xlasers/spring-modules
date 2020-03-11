package com.xlaser4j.demo.basic.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @package: com.xlaser4j.demo.basic.consumer
 * @author: Elijah.D
 * @time: 2020/2/5 21:25
 * @description:
 * @modified: Elijah.D
 */
@Component
public class TopicConsumer {
    /**
     * 订阅chinese
     *
     * @param msg msg
     */
    @RabbitListener(queues = "chinese")
    public void chinese(String msg) {
        System.out.println("chinese-消费消息: " + msg);
    }

    /**
     * 订阅english
     *
     * @param msg msg
     */
    @RabbitListener(queues = "english")
    public void english(String msg) {
        System.out.println("english-消费消息: " + msg);
    }

    /**
     * 订阅language
     *
     * @param msg msg
     */
    @RabbitListener(queues = "language")
    public void language(String msg) {
        System.out.println("language-消费消息: " + msg);
    }
}
