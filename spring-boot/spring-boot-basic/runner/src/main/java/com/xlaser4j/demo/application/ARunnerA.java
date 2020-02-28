package com.xlaser4j.demo.application;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @package: com.xlaser4j.demo.application
 * @author: Elijah.D
 * @time: 2019/12/14 14:03
 * @description:
 * @modified: Elijah.D
 */
@Order(100)
@Component
public class ARunnerA implements ApplicationRunner {
    /**
     * 启动后执行,arg是对启动参数的封装
     *
     * @param args incoming application arguments
     */
    @Override
    public void run(ApplicationArguments args) {
        System.out.println("=====ARunnerA=====执行开始=====");
        System.out.println(args.getNonOptionArgs());
        System.out.println(args.getOptionNames());
        System.out.println("=====ARunnerA=====执行完成=====\n");
    }
}
