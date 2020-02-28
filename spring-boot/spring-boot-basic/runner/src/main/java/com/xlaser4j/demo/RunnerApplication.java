package com.xlaser4j.demo;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 当我们希望在springboot容器后，执行一些操作的时候，可以使用ApplicationRunner或者CommandLineRunner.
 * <p>
 * 1.ApplicationRunner默认先于CommandLineRunner执行(order优先级相同情况下)
 * <p>
 * 2.{@link org.springframework.boot.ApplicationRunner#run}方法中的ApplicationArguments是启动参数的封装.
 * <p>
 * 3.{@link org.springframework.boot.CommandLineRunner#run}方法的arg数组等于传入的启动参数.
 * <p>
 * 4.多个实现类的执行顺序
 * 4.1-使用@Order(value=整数值)
 * 4.2-实现Ordered接口，在方法里return 一个顺序值
 *
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2019/12/14 13:55
 * @description: 测试两种runner
 * @modified: Elijah.D
 */
@SpringBootApplication
public class RunnerApplication {
    public static void main(String[] args) {
        System.out.println("=====RunnerApplication=====");
        System.out.println("=====args=====" + Arrays.toString(args));
        SpringApplication.run(RunnerApplication.class, args);
    }
}
