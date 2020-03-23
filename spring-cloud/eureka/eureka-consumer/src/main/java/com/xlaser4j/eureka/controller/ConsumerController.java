package com.xlaser4j.eureka.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;

import lombok.Cleanup;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @package: com.xlaser4j.eureka.controller
 * @author: Elijah.D
 * @time: 2020/3/22 15:19
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class ConsumerController {
    private final DiscoveryClient discoveryClient;

    private final RestTemplate restTemplate;

    private final RestTemplate restTemplateWithBalance;

    private final LoadBalancerClient balancerClient;

    public ConsumerController(DiscoveryClient discoveryClient, RestTemplate restTemplate, RestTemplate restTemplateWithBalance, LoadBalancerClient balancerClient) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
        this.restTemplateWithBalance = restTemplateWithBalance;
        this.balancerClient = balancerClient;
    }

    /**
     * 模拟Http原生调用服务,host端口等高度耦合
     *
     * @return
     */
    @GetMapping("/c1")
    public String http() throws IOException {

        URL url = new URL("http://localhost:8082/ep");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        if (connection.getResponseCode() == HttpStatus.OK.value()) {
            @Cleanup
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return reader.readLine();
        }
        return "调用失败!";
    }

    /**
     * 利用eureka-discover解耦调用
     *
     * @return
     */
    @GetMapping("/c2")
    public String httpWithBalance() throws IOException {

        // 通过jar修改端口,模拟2个provider的集群随机调用
        List<ServiceInstance> instances = discoveryClient.getInstances("eureka-provider");
        ServiceInstance instance = instances.get(0);
        if (instances.size() > 1 && new Random().nextInt(10) > 5) {
            instance = instances.get(1);
        }

        URL url = new URL("http://" + instance.getHost() + ":" + instance.getPort() + "/ep");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        if (connection.getResponseCode() == HttpStatus.OK.value()) {
            @Cleanup
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return reader.readLine();
        }
        return "调用失败!";
    }

    /**
     * spring提供了远程调用restTemplate工具
     *
     * @return
     */
    @GetMapping("/c3")
    public String restTemplate() {
        return restTemplate.getForObject("http://localhost:8082/ep", String.class);
    }

    /**
     * spring提供了远程调用restTemplate工具,加上负载均衡注解
     * <p>
     * 注意路径需要写服务名,不可以写精确的host,即使是对的也无法访问!
     *
     * @return
     */
    @GetMapping("/c4")
    public String restTemplateWithBalance() {
        return restTemplateWithBalance.getForObject("http://eureka-provider/ep", String.class);
    }

    /**
     * balancerClient负载均衡
     *
     * @return
     */
    @GetMapping("/c5")
    public String loadBalancerClient() {
        ServiceInstance instance = balancerClient.choose("eureka-provider");
        return restTemplate.getForObject(instance.getUri() + "/ep", String.class);
    }
}
