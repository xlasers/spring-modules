package com.xlaser4j.demo.dead.producer;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.xlaser4j.demo.dead.config.DeadLetterConfig.BU_EXCHANGE;

/**
 * @package: com.xlaser4j.demo.dead.producer
 * @author: Elijah.D
 * @time: 2020/3/12 11:07
 * @description: 死信队列发送消息测试
 * @modified: Elijah.D
 */
@RestController
public class DeadProducerController {
    private final RabbitTemplate template;

    public DeadProducerController(RabbitTemplate template) {
        this.template = template;
    }

    /**
     * 生产消息
     */
    @GetMapping("/dead")
    public String send() {
        template.convertAndSend(BU_EXCHANGE, null, "Test dead letter: " + new Date());
        return "业务消息生产成功: 业务队列A随机Nack,查看控制台打印信息,验证订阅消费,随机发送到死信队列A!";
    }
}
