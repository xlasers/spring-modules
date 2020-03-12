package com.xlaser4j.demo.delay.producer;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.xlaser4j.demo.delay.config.DeadDelayConfig.*;
import static com.xlaser4j.demo.delay.config.DelayConfig.DELAY_EXCHANGE;
import static com.xlaser4j.demo.delay.config.DelayConfig.DELAY_QUEUE_ROUTING_KEY;

/**
 * @package: com.xlaser4j.demo.dead.producer
 * @author: Elijah.D
 * @time: 2020/3/12 11:07
 * @description: 延迟队列发送消息测试
 * @modified: Elijah.D
 */
@RestController
@RequestMapping("/delay")
public class DelayProducerController {
    private final RabbitTemplate template;

    public DelayProducerController(RabbitTemplate template) {
        this.template = template;
    }

    /**
     * 队列TTL: 30s变成死信
     */
    @GetMapping("/a")
    public String sendToA() {
        template.convertAndSend(TTL_EXCHANGE, QUEUE_A_ROUTING_KEY, "Test 30s to dead letter: " + new Date());
        return "队列TTL: 查看控制台打印信息,验证订阅消费,30s后发送到死信队列A!";
    }

    /**
     * 队列TTL: 60s变成死信
     */
    @GetMapping("/b")
    public String sendToB() {
        template.convertAndSend(TTL_EXCHANGE, QUEUE_B_ROUTING_KEY, "Test 60s to dead letter: " + new Date());
        return "队列TTL: 查看控制台打印信息,验证订阅消费,60s后发送到死信队列B!";
    }

    /**
     * 消息TTL: 自定义过期time,变成死信
     */
    @GetMapping("/c")
    public String sendToC(Integer time) {
        template.convertAndSend(TTL_EXCHANGE, QUEUE_C_ROUTING_KEY, "Test custom time [" + time + "ms] to dead letter: " + new Date(), msg -> {
            msg.getMessageProperties().setExpiration(time.toString());
            return msg;
        });
        return "消息TTL: 查看控制台打印信息,验证订阅消费," + time + "ms后发送到死信队列C!";
    }

    /**
     * Delay插件: 自定义delayTime
     */
    @GetMapping("/d")
    public String sendToDelay(Integer time) {
        template.convertAndSend(DELAY_EXCHANGE, DELAY_QUEUE_ROUTING_KEY, "Test custom time [" + time + "ms] to dead letter: " + new Date(), msg -> {
            msg.getMessageProperties().setDelay(time);
            return msg;
        });
        return "消息Delay: 查看控制台打印信息,验证订阅消费," + time + "ms后发送到延迟队列!";
    }
}
