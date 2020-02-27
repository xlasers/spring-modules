package com.xlaser4j.demo.consumer;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.xlaser4j.demo.RedisMqApplication;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @package: com.xlaser4j.demo.consumer
 * @author: Elijah.D
 * @time: 2020/2/27 15:39
 * @description:
 * @modified: Elijah.D
 */
@Component
public class RedisConsumer {
    private final StringRedisTemplate template;

    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5, new ThreadFactoryBuilder().setNameFormat("delay-pool-%d").build());

    public RedisConsumer(StringRedisTemplate template) {
        this.template = template;
    }

    /**
     * 异步队列: 通过list结构的push/pop实现生产消费队列
     */
    //@Scheduled(cron = "0/3 * * * * ?")
    public void consumerA() {
        System.out.println("\n轮询消费list: " + new Date());
        String msg = template.opsForList().rightPop("queue");
        if (null != msg) {
            System.out.println("消息到达,开始消费: " + msg);
        }
    }

    /**
     * 异步延迟队列: 通过list结构的push/lpop/rpop实现生产消费延迟阻塞队列
     * <p>
     * 当轮询线程发现list为空,就会sleep阻塞,直到有数据立刻苏醒消费,超时会自动断开连接
     * <p>
     * fixRate间隔1ms,当消息到达,当前线程消费不阻塞,或者超时断开连接,都表现为当前线程执行结束,schedule等待1ms立即启动
     * 第二次消费任务;整体表现为,一分钟轮询一次,但是有消息到达会立刻消费,减少上述那种空轮询次数
     * <p>
     * 注意redis是单线程操作,这里的initial延迟1分钟方便测试A任务执行,过了一分钟B任务开始阻塞,上面的A任务中的redis命令
     * 就会阻塞,停止控制台输出log,直到这个任务超时或者消费到msg才会执行一次,因为这里的fixed设置1ms,所以A任务能执行的时
     * 间很短,如果设置fixed大一些,就会发现B每次消费超时的同时,A的日志也会多输出一些(也可以注释scheduled单个执行测试效果)
     */
    //@Scheduled(fixedDelay = 1, initialDelay = 60000)
    public void consumerB() {
        System.out.println("\n轮询消费blocking: " + new Date());
        String msg = null;
        try {
            msg = template.opsForList().rightPop("queue-blocking", 1L, TimeUnit.MINUTES);
        } catch (Exception e) {
            System.out.println("阻塞线程等待1min超时,redis自动断开连接,schedule认为本次任务结束,间隔1ms发起第二次轮询!");
        }
        if (null != msg) {
            System.out.println("消息到达,开始消费: " + msg);
        }
    }

    /**
     * 异步延时队列: 通过zset结构实现消息的延时消费,不是立刻消费,是到指定时间才消费
     * <p>
     * 生产消息,score为延迟30s,消费端通过zrangebyscore轮询获取到延迟时间的第一条立刻消费
     * <p>
     * 多线程保障可用性,zrem返回值决定哪个线程抢到消费权
     * <p>
     * 不用轮询触发,异步启动consumer即可{@link RedisMqApplication#consumer()}
     */
    @SneakyThrows
    @SuppressWarnings("ConstantConditions")
    public void consumerC() {
        System.out.println("\n=========== " + new Date() + ": 启动消费delaying线程,此时消息数量信息如下: ===========\n");
        executor.execute(() -> {
            while (!Thread.interrupted()) {
                Set<String> msgs = template.opsForZSet().rangeByScore("queue-delaying", 0, System.currentTimeMillis(), 0, 1);
                if (msgs.isEmpty()) {
                    /// System.out.println(Thread.currentThread().getName() + ": 消息延时,未到消费时间,睡眠3s继续轮询...");
                    continue;
                }
                String msg = msgs.iterator().next();
                System.out.println("\n延时消息,已经到时间,即刻消费: " + msg);
                if (template.opsForZSet().remove("queue-delaying", msg) > 0) {
                    System.out.println(new Date() + ": 线程-" + Thread.currentThread().getName() + " 抢到消息,开始消费: " + msg);
                }
            }
        });
    }
}
