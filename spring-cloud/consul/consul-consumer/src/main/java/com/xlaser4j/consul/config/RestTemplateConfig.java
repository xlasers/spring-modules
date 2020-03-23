package com.xlaser4j.consul.config;

import java.net.URI;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequest;
import org.springframework.cloud.client.loadbalancer.RetryLoadBalancerInterceptor;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @package: com.xlaser4j.consul.config
 * @author: Elijah.D
 * @time: 2020/3/22 15:54
 * @description: Spring提供的RestTemplate
 * @modified: Elijah.D
 */
@Configuration
public class RestTemplateConfig {
    /**
     * 正常的restTemplate,调用路径必须准确可访问: http://localhost:8082/ep
     *
     * @return rest
     */
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 带有负载均衡注解的restTemplate,调用路径必须是服务名: http://instance-name/ep
     * <p>
     * 实际上是通过Ribbon实现的<b>客户端<b/>负载均衡策略,像nginx是服务端负载均衡,客户端不知道具体提供服务的信息,
     * 而Ribbon是从eureka上获取到所有提供服务的元数据之后,根据相关策略实现选择负载
     * {@link RibbonLoadBalancerClient#execute(String, LoadBalancerRequest, Object)}
     * <p>
     * 具体步骤:
     * 1.获取提供给服务集群的列表信息DiscoveryEnabledNIWSServerList#obtainServersViaDiscovery(),
     * 根据策略选择一个服务{@link ZoneAwareLoadBalancer#chooseServer(Object)},{@link com.netflix.loadbalancer.PredicateBasedRule#choose(Object)}
     * <p>
     * 2.重构url,因为url是服务名,需要替换成选择的服务host/ip{@link com.netflix.loadbalancer.LoadBalancerContext#reconstructURIWithServer(Server, URI)}
     * <p>
     * 3.通过拦截器,把上述两步的内容注入到restTemplate种{@link LoadBalancerAutoConfiguration.RetryInterceptorAutoConfiguration#restTemplateCustomizer(RetryLoadBalancerInterceptor)}
     *
     * @return rest
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplateWithBalance() {
        return new RestTemplate();
    }
}
