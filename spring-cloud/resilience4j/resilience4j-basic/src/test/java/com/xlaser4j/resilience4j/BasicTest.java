package com.xlaser4j.resilience4j;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.CheckedFunction0;
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
    public void testCircuitBreaker() {

        CircuitBreakerRegistry defaultReg = CircuitBreakerRegistry.ofDefaults();
        CircuitBreaker defaultBreaker = defaultReg.circuitBreaker("default");
        System.out.println("\n直接获取默认的Registry: " + defaultBreaker);

        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindow(5, 2, COUNT_BASED)
                .permittedNumberOfCallsInHalfOpenState(2).build();

        CircuitBreakerRegistry customReg = CircuitBreakerRegistry.of(config);
        CircuitBreaker customBreaker = customReg.circuitBreaker("customReg");
        System.out.println("\n添加相关配置的Registry: " + customBreaker);

        System.out.println(customBreaker.getState());
        customBreaker.onError(0, TimeUnit.SECONDS, new RuntimeException());
        System.out.println(customBreaker.getState());
        customBreaker.onSuccess(0, TimeUnit.SECONDS);
        System.out.println(customBreaker.getState());
        customBreaker.onSuccess(0, TimeUnit.SECONDS);
        System.out.println(customBreaker.getState());
        customBreaker.onSuccess(0, TimeUnit.SECONDS);
        System.out.println(customBreaker.getState());
        customBreaker.onSuccess(0, TimeUnit.SECONDS);
        System.out.println(customBreaker.getState());
        customBreaker.onError(0, TimeUnit.SECONDS, new RuntimeException());

        CheckedFunction0<String> supplier = CircuitBreaker.decorateCheckedSupplier(customBreaker, () -> "Hello Resilience4j");
        Try<String> result = Try.of(supplier).map(v -> v + " - Hello Again!");
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }
}
