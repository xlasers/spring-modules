package com.xlaser4j.demo.delay.consumer;

import java.io.IOException;
import java.util.Date;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.xlaser4j.demo.delay.config.DelayConfig.DELAY_QUEUE;

/**
 * @package: com.xlaser4j.demo.dead.consumer
 * @author: Elijah.D
 * @time: 2020/3/12 9:47
 * @description: 使用延迟插件实现队列的延迟消费
 * @modified: Elijah.D
 */
@Component
public class DelayConsumer {
    /**
     * 延迟队列自定义延迟时间消费
     * <p>
     * 会根据延迟时间大小自动排序及时消费
     *
     * @param msg     消息
     * @param channel channel
     * @throws IOException IO
     */
    @RabbitListener(queues = DELAY_QUEUE)
    public void consumeA(Message msg, Channel channel) throws IOException {
        System.out.println(new Date() + ": 通过rabbit的延迟插件:rabbitmq_delayed_message_exchange,实现延迟队列的自定义延迟时间消费: " + new String(msg.getBody()));
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
    }
}
