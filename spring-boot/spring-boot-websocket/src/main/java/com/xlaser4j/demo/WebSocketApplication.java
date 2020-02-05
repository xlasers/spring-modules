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
public class WebSocketApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================websocket是http的一种协议升级,请求头中两个字段标识connection:upgrade,upgrade:socket.==============================\n");
        System.out.println("\n==============================浏览器F12控制台console输出详细建立连接发送消息过程.==============================\n");
        System.out.println(">>> CONNECT\n<<< CONNECTED\n>>> SUBSCRIBE\n>>> SEND\n<<< MESSAGE");
        SpringApplication.run(WebSocketApplication.class, args);
    }
}