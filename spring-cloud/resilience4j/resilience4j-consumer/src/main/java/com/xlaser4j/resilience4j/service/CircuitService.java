package com.xlaser4j.resilience4j.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @package: com.xlaser4j.resilience4j.service
 * @author: Elijah.D
 * @time: 2020/3/29 20:31
 * @description:
 * @modified: Elijah.D
 */
@Service
@CircuitBreaker(name = "backendA", fallbackMethod = "circuitError")
public class CircuitService {
    private final RestTemplate template;

    public CircuitService(RestTemplate template) {
        this.template = template;
    }

    /**
     * restTemplate简单测试即可
     *
     * @return msg
     */
    public String circuit() {
        return template.getForObject("http://resilience4j-provider/pc", String.class);
    }

    /**
     * 配置降级调用,注意这里必须加入参数Throwable,否则fallback方法找不到{@link NoSuchMethodException}
     * <p>
     * class java.lang.String class com.xlaser4j.resilience4j.service.RetryService.retryError(,class java.lang.Throwable)
     *
     * @return msg
     */
    public String circuitError(Throwable throwable) {
        System.out.println("\n========== 异常原因: " + throwable.getMessage() + " ==========\n");
        return "断路器生效: 调用失败,回调容错方法,降级处理!";
    }
}
