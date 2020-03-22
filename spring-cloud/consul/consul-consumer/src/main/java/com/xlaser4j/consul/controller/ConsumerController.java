package com.xlaser4j.consul.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @package: com.xlaser4j.consul.controller
 * @author: Elijah.D
 * @time: 2020/3/22 15:13
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class ConsumerController {
    private final DiscoveryClient discoveryClient;

    private final LoadBalancerClient balancerClient;

    private final RestTemplate restTemplate;

    public ConsumerController(DiscoveryClient discoveryClient, LoadBalancerClient balancerClient, RestTemplate restTemplate) {
        this.discoveryClient = discoveryClient;
        this.balancerClient = balancerClient;
        this.restTemplate = restTemplate;
    }

    /**
     * 模拟消费者
     *
     * @return
     */
    @GetMapping("/c1")
    public String provider() {
        System.out.println(discoveryClient.getInstances("consul-provider").get(0));

        ServiceInstance instance = balancerClient.choose("consul-provider");
        System.out.println(instance);

        return restTemplate.getForObject(instance.getUri() + "/cp", String.class);
    }
}
