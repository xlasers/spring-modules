package com.xlaser4j.demo.delay.consumer;

import java.io.IOException;
import java.util.Date;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.xlaser4j.demo.delay.config.DeadDelayConfig.*;

/**
 * @package: com.xlaser4j.demo.dead.consumer
 * @author: Elijah.D
 * @time: 2020/3/12 9:47
 * @description: 监听死信队列, 队列的消息ttl之后自动变成死信
 * @modified: Elijah.D
 */
@Component
public class DeadLetterDelayConsumer {
    /**
     * 消费A: 队列的ttl-30s
     *
     * @param msg     消息
     * @param channel channel
     * @throws IOException IO
     */
    @RabbitListener(queues = DEAD_DELAY_QUEUE_A)
    public void consumeA(Message msg, Channel channel) throws IOException {
        System.out.println("查看成为死信的信息: " + msg.getMessageProperties().getHeaders());
        System.out.println(new Date() + ": 死信队列A收到,延迟队列A,30s后变成死信的消息: " + new String(msg.getBody()));
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 消费B:  队列的ttl-60s
     *
     * @param msg     消息
     * @param channel channel
     * @throws IOException IO
     */
    @RabbitListener(queues = DEAD_DELAY_QUEUE_B)
    public void consumeB(Message msg, Channel channel) throws IOException {
        System.out.println(new Date() + ": 死信队列B收到,延迟队列B,60s后变成死信的消息: " + new String(msg.getBody()));
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 消费C:  自定义消息的ttl
     * <p>
     * 测试不同的延迟时间发现: 如果先入队列的ttl时间长于后入队列的ttl,由于队列的先后顺序,后者直到前者成为死信之后
     * 才能变成死信被消费,也即是不能及时成为死信,如果业务有这种后入队列的消息ttl时间也大于等于前者可以使用,如果不是则
     * 需要使用{@link com.xlaser4j.demo.delay.config.DelayConfig}插件实现
     *
     * @param msg     消息
     * @param channel channel
     * @throws IOException IO
     */
    @RabbitListener(queues = DEAD_DELAY_QUEUE_C)
    public void consumeC(Message msg, Channel channel) throws IOException {
        System.out.println(new Date() + ": 死信队列C收到,延迟队列C,自定义时间后变成死信的消息: " + new String(msg.getBody()));
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
    }
}
