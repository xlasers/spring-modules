package com.xlaser4j.demo.dead.consumer;

import java.io.IOException;
import java.util.Random;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.xlaser4j.demo.dead.config.DeadLetterConfig.BU_QUEUE_A;
import static com.xlaser4j.demo.dead.config.DeadLetterConfig.BU_QUEUE_B;

/**
 * @package: com.xlaser4j.demo.dead.consumer
 * @author: Elijah.D
 * @time: 2020/3/12 9:45
 * @description: 业务消费
 * @modified: Elijah.D
 */
@Component
public class BusinessConsumer {
    /**
     * 消费A
     *
     * @param msg     消息
     * @param channel channel
     */
    @RabbitListener(queues = BU_QUEUE_A)
    public void consumeA(Message msg, Channel channel) throws IOException {
        System.out.println("收到业务消息A: " + new String(msg.getBody()));

        // 随机模拟: 被Nack或Reject的消息由RabbitMQ投递到死信交换机中
        if (new Random().nextInt() % 2 == 0) {
            System.out.println("业务A,模拟异常: Nack!!!");
            channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, false);
        } else {
            System.out.println("业务A,正常消费: Ack!");
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
        }
    }

    /**
     * 消费B
     *
     * @param msg     消息
     * @param channel channel
     * @throws IOException IO
     */
    @RabbitListener(queues = BU_QUEUE_B)
    public void consumeB(Message msg, Channel channel) throws IOException {
        System.out.println("收到业务消息B: " + new String(msg.getBody()));
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
    }
}
