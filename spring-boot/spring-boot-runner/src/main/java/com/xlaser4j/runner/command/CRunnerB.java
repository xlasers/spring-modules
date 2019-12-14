package com.xlaser4j.runner.command;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @package: com.xlaser4j.runner.command
 * @author: Elijah.D
 * @time: 2019/12/14 14:09
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@Order(80)
@Component
public class CRunnerB implements CommandLineRunner {
    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("=====CRunnerB=====执行开始=====");
        System.out.println("=====args=====" + Arrays.toString(args));
        System.out.println("=====CRunnerB=====执行完成=====\n");
    }
}
