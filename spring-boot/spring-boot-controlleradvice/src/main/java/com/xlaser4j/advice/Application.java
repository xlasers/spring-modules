package com.xlaser4j.advice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * {@link ExceptionHandler @ExceptionHandler},
 * {@link InitBinder @InitBinder}
 * {@link ModelAttribute @ModelAttribute}
 * <p>
 * controllerAdvice是controller的增强,针对controller的功能增强
 *
 * @package: com.xlaser4j.advice
 * @author: Elijah.D
 * @time: 2020/0/0 00:00
 * @description: Start App Template
 * @modified: Elijah.D
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("\n==============================@ControllerAdvice的三种作用1:统一处理全局异常信息==============================\n");
        System.out.println("==============================@ControllerAdvice的三种作用2:预设置全局数据,所有controller都可以访问==============================\n");
        System.out.println("==============================@ControllerAdvice的三种作用3:参数预处理==============================\n");
        SpringApplication.run(Application.class, args);
    }
}
