package com.xlaser4j.demo.producer;

import java.util.Iterator;
import java.util.Random;

import com.xlaser4j.demo.consumer.RedisConsumer;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
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

    private final RedisConsumer consumer;

    public ProducerController(StringRedisTemplate template, RedisConsumer consumer) {
        this.template = template;
        this.consumer = consumer;
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
     * 当轮询线程发现list为空,就会sleep阻塞,直到有数据立刻苏醒消费,超时会断开连接,注意捕捉异常重试等策略
     */
    @GetMapping("/b")
    public String produceB() {
        String msg = "Blocking " + new Random().nextInt();
        template.opsForList().leftPush("queue-blocking", msg);
        return msg;
    }

    /**
     * 异步延时队列: 通过zset结构实现消息的延时消费,不是立刻消费,是到指定时间才消费
     * <p>
     * 生产消息,score为延迟30s,消费端通过zrangebyscore轮询获取到延迟时间的第一条立刻消费
     */
    @SneakyThrows
    @GetMapping("/c")
    public String produceC() {
        String msg = "Delaying ";
        int count = 10;
        for (int i = 0; i < count; i++) {
            Iterator<ZSetOperations.TypedTuple<String>> it = template.opsForZSet().rangeWithScores("queue-delaying", 0, -1).iterator();
            if (it.hasNext()) {
                ZSetOperations.TypedTuple<String> next = it.next();
                System.out.println("value: " + next.getValue() + " score: " + next.getScore());
            }
            Thread.sleep(2000);
            template.opsForZSet().add("queue-delaying", i + "-" + msg + new Random().nextInt(), System.currentTimeMillis() + 10000);
        }
        return msg;
    }
}