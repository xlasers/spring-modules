package com.xlaser4j.resilience4j;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;
import org.junit.Test;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED;

/**
 * @package: com.xlaser4j.resilience4j
 * @author: Elijah.D
 * @time: 2020/3/26 17:37
 * @description:
 * @modified: Elijah.D
 */
public class BasicTest {
    /**
     * CircuitBreaker: 断路器功能
     */
    @Test
    public void testCircuitBreaker() throws InterruptedException {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindow(5, 2, COUNT_BASED)
                .permittedNumberOfCallsInHalfOpenState(2).build();
        CircuitBreaker circuitBreaker = CircuitBreaker.of("customCircuitBreaker", config);

        System.out.println(circuitBreaker.getState());
        circuitBreaker.onSuccess(0, TimeUnit.SECONDS);
        circuitBreaker.onSuccess(0, TimeUnit.SECONDS);
        circuitBreaker.onError(0, TimeUnit.SECONDS, new RuntimeException("测试异常统计: 依赖中加入sl4j查看详细debug信息!"));
        circuitBreaker.onSuccess(0, TimeUnit.SECONDS);
        circuitBreaker.onError(0, TimeUnit.SECONDS, new RuntimeException("测试异常统计: 一个滑动窗口是5个,两次错误,不会开启断路器!"));
        System.out.println(circuitBreaker.getState());
        circuitBreaker.onSuccess(0, TimeUnit.SECONDS);
        circuitBreaker.onError(0, TimeUnit.SECONDS, new RuntimeException("测试异常统计: 第七次请求处于第二次滑动窗口,同时设置2个开始统计错误率,也就是第二次的滑动窗口前两个有一个错误,也就是50%错误率,开启断路器!"));
        System.out.println(circuitBreaker.getState());

        System.out.println("waitDurationInOpenState: 这只了断路器打开的时间1s,因此这里睡眠1s就会进入half open状态,完全打开状态无法获取结果,下面会报错!");
        Thread.sleep(1000);
        CheckedFunction0<String> supplier = CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> "Hello Resilience4j");
        Try<String> result = Try.of(supplier).map(v -> v + " - Hello Again!");
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }

    /**
     * RateLimiter: 限流功能
     */
    @Test
    public void testRateLimiter() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(3000))
                .limitRefreshPeriod(Duration.ofMillis(3000))
                .limitForPeriod(3).build();
        RateLimiter limiter = RateLimiter.of("customLimiter", config);

        CheckedRunnable runnable = RateLimiter.decorateCheckedRunnable(limiter, () -> System.out.println(new Date()));
        System.out.println("每3s限制请求三个: 查看控制台输出时间周期;同时limitRefreshPeriod(一个周期的时间) >= timeoutDuration(???)");
        Try.run(runnable)
                .andThenTry(runnable)
                .andThenTry(runnable)
                .andThenTry(runnable)
                .andThenTry(runnable)
                .andThenTry(runnable)
                .andThenTry(runnable)
                .onFailure(t -> System.out.println(t.getMessage()));
    }

    /**
     * Retry: 重试功能
     */
    @Test
    public void testRetry() {
        RetryConfig config = RetryConfig.custom()
                .retryExceptions(RuntimeException.class)
                .maxAttempts(5)
                .waitDuration(Duration.ofMillis(500))
                .build();
        Retry retry = Retry.of("customRetry", config);

        AtomicInteger count = new AtomicInteger(1);
        Runnable runnable = Retry.decorateRunnable(retry, () -> {
            System.out.println("执行第 " + count + " 次");
            if (count.getAndIncrement() < 3) {
                throw new RuntimeException("测试重试: 前三次count小于3会抛出异常,但是设置了异常会重试5次,因此测试最终在第四次的时候执行成功,如果maxAttempt设置成2则重试两次,仍然执行失败!");
            }
        });

        runnable.run();
    }
}