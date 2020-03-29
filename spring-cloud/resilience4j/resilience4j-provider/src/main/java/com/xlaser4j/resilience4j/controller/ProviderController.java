package com.xlaser4j.resilience4j.controller;

import java.util.Date;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.resilience4j.controller
 * @author: Elijah.D
 * @time: 2020/3/29 18:26
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class ProviderController {
    /**
     * 模拟: Retry
     *
     * @return
     */
    @GetMapping("/pr")
    public String retry() throws InterruptedException {
        System.out.println("========== Retry-消费端-正在请求: " + new Date() + " ==========");
        if (true) {
            Thread.sleep(2000);
            throw new RuntimeException("这里抛出异常,consumer重试(max-retry-attempts)5次,查看provider控制台打印,每个两秒打印一次日志!");
        }
        return "Hello Retry!";
    }

    /**
     * 模拟: Circuit
     *
     * @return
     */
    @GetMapping("/pc")
    public String circuit() {
        System.out.println("========== Circuit-消费端-正在请求: " + new Date() + " ==========");
        if (true) {
            throw new RuntimeException("这里抛出异常,consumer收到异常信息,断路器打开!");
        }
        return "Hello circuit!";
    }

    /**
     * 模拟: RateLimiter
     *
     * @return
     */
    @RateLimiter(name = "backendA")
    @GetMapping("/prl")
    public String rateLimiter() {
        System.out.println("========== RateLimiter-消费端-正在请求(这里注解限流,3s中处理三个请求,查看控制台输出每隔3s处理三个): " + new Date() + " ==========");
        return "Hello rateLimiter!";
    }
}
