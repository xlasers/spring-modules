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
public class MailApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================邮件发送涉及的两种通信协议:SMTP和POP3(IMAP是对POP3扩展增强)==============================\n");
        System.out.println("\n==============================SMTP:Simple Mail Transfer Protocol,译作简单邮件传输协议,只负责发送邮件,如果是相同domain,那么smtp直接转送到pop3,如果不是eg:qq.com发到gmail.com,那么先是转到后者的smtp,然后后者分发到自己的pop3服务,这就是利用的imtp协议.==============================\n");
        System.out.println("\n==============================POP3:Post Office Protocol,译作邮局协议只负责存储邮件,pop3存储了邮件,用户登陆邮箱从pop3中查看邮件内容,这个时候就是用的pop3协议(旧协议,pop4正在协商?)==============================\n");
        SpringApplication.run(MailApplication.class, args);
    }
}