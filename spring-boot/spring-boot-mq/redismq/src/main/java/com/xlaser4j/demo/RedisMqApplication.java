package com.xlaser4j.demo;

import javax.annotation.PostConstruct;

import com.xlaser4j.demo.consumer.RedisConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/0/0 00:00
 * @description: Start App Template
 * @modified: Elijah.D
 */
@EnableScheduling
@SpringBootApplication
public class RedisMqApplication {
    private final RedisConsumer consumer;

    public RedisMqApplication(RedisConsumer consumer) {
        this.consumer = consumer;
    }

    public static void main(String[] args) {
        System.out.println("\n==============================如果仅仅是简单的生产消费队列,可以通过redis简单实现,而省去了那些专业mq的配置==============================\n");
        SpringApplication.run(RedisMqApplication.class, args);
    }

    @PostConstruct
    public void consumer() {
        consumer.consumerC();
    }
}