package com.xlaser4j.demo.config;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/2/5 15:36
 * @description:
 * @modified: Elijah.D
 */
@Configuration
public class ActiveMqConfig {
    /**
     * JMS的api{@link Queue}由activeMQ实现
     * <p>
     * 定义队列的名字:object-queue
     *
     * @return
     */
    @Bean
    Queue objectQueue() {
        return new ActiveMQQueue("object-queue");
    }

    /**
     * JMS的api{@link Queue}由activeMQ实现
     * <p>
     * 定义队列的名字:string-queue
     *
     * @return
     */
    @Bean
    Queue stringQueue() {
        return new ActiveMQQueue("string-queue");
    }
}
