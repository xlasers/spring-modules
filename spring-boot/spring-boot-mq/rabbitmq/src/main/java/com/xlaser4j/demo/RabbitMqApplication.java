package com.xlaser4j.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/0/0 00:00
 * @description: Start App Template
 * @modified: Elijah.D
 */
@SpringBootApplication
public class RabbitMqApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================JMS:Java Message Service是一个java平台的api接口规范,仅仅支持java平台,类似于jdbc规范,可以有不同的实现:activemq就是jms的是一种实现.==============================\n");
        System.out.println("\n==============================AMQP:Advanced Message Queuing Protocol是一种链接协议,定义了网络通讯间的数据格式,支持跨平台,类似于http协议,支持即可完成通讯:rabbitmq就是通过amqp实现.===============================\n");
        System.out.println("\n==============================RabbitMQ默认:通讯端口5672,web管理端口15672,login登陆:guest/guest==============================\n");
        System.out.println("\n==============================RabbitMQ是通过exchange#routingKey来完成消息的分发,一共四种策略:direct,fanout,topic,headers==============================\n");
        SpringApplication.run(RabbitMqApplication.class, args);
    }
}