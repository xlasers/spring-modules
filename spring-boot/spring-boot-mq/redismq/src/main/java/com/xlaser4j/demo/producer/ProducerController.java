package com.xlaser4j.demo.producer;

import java.util.Random;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.demo.producer
 * @author: Elijah.D
 * @time: 2020/2/27 15:33
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class ProducerController {
    private final StringRedisTemplate template;

    public ProducerController(StringRedisTemplate template) {
        this.template = template;
    }

    /**
     * 异步队列: 通过list结构的push/pop实现生产消费队列
     */
    @GetMapping("/a")
    public String produceA() {
        String msg = "Hello Consumer " + new Random().nextInt();
        template.opsForList().leftPush("queue", msg);
        return msg;
    }

    /**
     * 异步延迟队列: 通过list结构的push/lpop/rpop实现生产消费延迟阻塞队列
     * <p>
     * 当轮询线程发现list为空,就会sleep阻塞,直到有数据立刻苏醒消费
     */
    @GetMapping("/b")
    public String produceB() {
        String msg = "Blocking " + new Random().nextInt();
        template.opsForList().leftPush("queue-blocking", msg);
        return msg;
    }
}