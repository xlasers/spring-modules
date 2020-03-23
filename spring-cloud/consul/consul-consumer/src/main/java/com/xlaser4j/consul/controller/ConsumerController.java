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

    private final RestTemplate restTemplate;

    private final LoadBalancerClient balancerClient;

    private final RestTemplate restTemplateWithBalance;

    public ConsumerController(DiscoveryClient discoveryClient, LoadBalancerClient balancerClient, RestTemplate restTemplate, RestTemplate restTemplateWithBalance) {
        this.discoveryClient = discoveryClient;
        this.balancerClient = balancerClient;
        this.restTemplate = restTemplate;
        this.restTemplateWithBalance = restTemplateWithBalance;
    }

    /**
     * LoadBalancerClient可以直接负载均衡
     *
     * @return msg
     */
    @GetMapping("/c1")
    public String restTemplate() {
        System.out.println(discoveryClient.getInstances("consul-provider").get(0));

        ServiceInstance instance = balancerClient.choose("consul-provider");
        return restTemplate.getForObject(instance.getUri() + "/cp", String.class);
    }

    /**
     * 使用@LoadBalanced注解同样可以负载均衡
     *
     * @return msg
     */
    @GetMapping("/c2")
    public String restTemplateWithBalance() {
        return restTemplateWithBalance.getForObject("http://consul-provider/cp", String.class);
    }
}
