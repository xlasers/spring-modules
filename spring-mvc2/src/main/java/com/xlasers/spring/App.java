package com.xlasers.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The type App.
 *
 * @package: com.xlasers.spring
 * @author: Elijah.D
 * @time: CreateAt 2018/9/21 && 16:52
 * @description:
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Slf4j
public class App {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        MsgService msgService = (MsgService) context.getBean("msgService");
        log.info("【spring】msgService 调用, msg: {}",msgService.getMsg());

        MsgPrinter msgPrinter = context.getBean(MsgPrinter.class);
        msgPrinter.printMsg();



    }
}
