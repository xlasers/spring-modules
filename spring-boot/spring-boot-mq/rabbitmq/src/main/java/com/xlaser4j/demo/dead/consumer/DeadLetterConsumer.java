package com.xlaser4j.demo.dead.consumer;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.xlaser4j.demo.dead.config.DeadLetterConfig.DEAD_QUEUE_A;
import static com.xlaser4j.demo.dead.config.DeadLetterConfig.DEAD_QUEUE_B;

/**
 * @package: com.xlaser4j.demo.dead.consumer
 * @author: Elijah.D
 * @time: 2020/3/12 9:47
 * @description:
 * @modified: Elijah.D
 */
@Component
public class DeadLetterConsumer {
    /**
     * 消费A
     *
     * @param msg     消息
     * @param channel channel
     * @throws IOException IO
     */
    @RabbitListener(queues = DEAD_QUEUE_A)
    public void consumeA(Message msg, Channel channel) throws IOException {
        System.out.println("死信队列A收到,业务消息A发生Nack产生的死信: " + new String(msg.getBody()));
        System.out.println("查看成为死信的信息: " + msg.getMessageProperties().getHeaders());
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 消费B
     *
     * @param msg     消息
     * @param channel channel
     * @throws IOException IO
     */
    @RabbitListener(queues = DEAD_QUEUE_B)
    public void consumeB(Message msg, Channel channel) throws IOException {
        System.out.println("死信队列B收到: " + new String(msg.getBody()));
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
    }
}
