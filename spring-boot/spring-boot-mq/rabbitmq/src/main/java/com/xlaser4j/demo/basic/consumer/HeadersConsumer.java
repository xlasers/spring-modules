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
public class HeadersConsumer {
    /**
     * 订阅key
     * <p>
     * headers模式下需要{@link org.springframework.amqp.core.Message}对象传输,byte数组消费
     *
     * @param msg msg
     */
    @RabbitListener(queues = "key")
    public void key(byte[] msg) {
        System.out.println("key-消费消息: " + new String(msg, 0, msg.length));
    }

    /**
     * 订阅keyValue
     * <p>
     * headers模式下需要{@link org.springframework.amqp.core.Message}对象传输,byte数组消费
     *
     * @param msg msg
     */
    @RabbitListener(queues = "keyValue")
    public void keyValue(byte[] msg) {
        System.out.println("keyValue-消费消息: " + new String(msg, 0, msg.length));
    }

    /**
     * 订阅allKeyValue
     * <p>
     * headers模式下需要{@link org.springframework.amqp.core.Message}对象传输,byte数组消费
     *
     * @param msg msg
     */
    @RabbitListener(queues = "allKeyValue")
    public void allKeyValue(byte[] msg) {
        System.out.println("allKeyValue-消费消息: " + new String(msg, 0, msg.length));
    }
}
