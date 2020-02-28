package com.xlaser4j.demo.command;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @package: com.xlaser4j.demo.command
 * @author: Elijah.D
 * @time: 2019/12/14 14:09
 * @description:
 * @modified: Elijah.D
 */
@Order(100)
@Component
public class CRunnerA implements CommandLineRunner {
    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("=====CRunnerA=====执行开始=====");
        System.out.println("=====args=====" + Arrays.toString(args));
        System.out.println("=====CRunnerA=====执行完成=====\n");
    }
}
