package com.xlaser4j.demo.basic.producer;

import com.xlaser4j.demo.basic.config.DirectExchangeConfig;
import com.xlaser4j.demo.basic.config.FanoutExchangeConfig;
import com.xlaser4j.demo.basic.config.HeadersExchangeConfig;
import com.xlaser4j.demo.basic.config.TopicExchangeConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注意配置文件中为了测试死信队列,提交模式设置了manual,而basic这里的模块消息只是简单地打印,
 * 并没有手动ack提交,所以每次重启都会消费打印!
 *
 * @package: com.xlaser4j.demo.basic.producer
 * @author: Elijah.D
 * @time: 2020/2/5 21:58
 * @description: 测试四种exchange的功能
 * @modified: Elijah.D
 */
@RestController
public class BasicProducerController {
    private final RabbitTemplate template;

    public BasicProducerController(RabbitTemplate template) {
        this.template = template;
    }

    /**
     * 使用频率高,利用routingKey通配符,灵活配置
     */
    @GetMapping("/t")
    public String topic() {
        template.convertAndSend(TopicExchangeConfig.EXCHANGE_NAME, "test.english", "Test topic exchange english!");
        template.convertAndSend(TopicExchangeConfig.EXCHANGE_NAME, "chinese.test", "Test topic exchange chinese!");
        template.convertAndSend(TopicExchangeConfig.EXCHANGE_NAME, "chinese.language.english", "Test topic exchange language!");
        return "topic-消息生产成功: 查看控制台打印信息,验证订阅消费!";
    }

    /**
     * 默认策略,分发消息到:queue名字等于分发时指定的routingKey的queue
     */
    @GetMapping("/d")
    public String direct() {
        template.convertAndSend(DirectExchangeConfig.EXCHANGE_NAME, "direct-key", "Test direct exchange!");
        return "direct-消息生产成功: 查看控制台打印信息,验证订阅消费!";
    }

    /**
     * 与routingKey无关,直接分发到绑定fanoutExchange的queue
     */
    @GetMapping("/f")
    public String fanout() {
        template.convertAndSend(FanoutExchangeConfig.EXCHANGE_NAME, null, "Test fanout exchange!");
        return "fanout-消息生产成功: 查看控制台打印信息,验证订阅消费!";
    }

    /**
     * 通过header做出分发策略,与routingKey无关,使用频率低
     */
    @GetMapping("/h")
    public String headers() {
        Message key = MessageBuilder.withBody("Test headers exchange key!".getBytes()).setHeader("key", "anyValue").build();
        template.convertAndSend(HeadersExchangeConfig.EXCHANGE_NAME, null, key);

        Message keyValue = MessageBuilder.withBody("Test headers exchange keyValue!".getBytes()).setHeader("key", "value").build();
        template.convertAndSend(HeadersExchangeConfig.EXCHANGE_NAME, null, keyValue);

        Message allKeyValue = MessageBuilder.withBody("Test headers exchange allKeyValue!".getBytes()).setHeader("key1", "value1").setHeader("key2", "value2").build();
        template.convertAndSend(HeadersExchangeConfig.EXCHANGE_NAME, null, allKeyValue);

        return "headers-消息生产成功: 查看控制台打印信息,验证订阅消费!";
    }
}
