package com.xlaser4j.resilience4j.controller;

import com.xlaser4j.resilience4j.service.CircuitService;
import com.xlaser4j.resilience4j.service.RetryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @package: com.xlaser4j.resilience4j.controller
 * @author: Elijah.D
 * @time: 2020/3/29 20:35
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class ConsumerController {
    private final RetryService retryService;

    private final RestTemplate restTemplate;

    private final CircuitService circuitService;

    public ConsumerController(RetryService retryService, RestTemplate restTemplate, CircuitService circuitService) {
        this.retryService = retryService;
        this.restTemplate = restTemplate;
        this.circuitService = circuitService;
    }

    @GetMapping("/cr")
    public String retry() {
        return retryService.retry();
    }

    @GetMapping("/cc")
    public String circuit() {
        return circuitService.circuit();
    }

    @GetMapping("/crl")
    public String rateLimiter() {
        for (int i = 0; i < 10; i++) {
            restTemplate.getForObject("http://resilience4j-provider/prl", String.class);
        }
        return "连续请求10次服务端,查看控制台日志,每隔3s处理三条请求!";
    }
}
