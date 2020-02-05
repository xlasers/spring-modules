package com.xlaser4j.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/2/5 10:24
 * @description:
 * @modified: Elijah.D
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    /**
     * 1.设置消息代理prefix路径,broker收到消息后通过/topic/**广播出去,订阅这个/topic/**的就会收到消息
     * Enable a simple message broker and configure one or more prefixes to filter destinations
     * targeting the broker (e.g. destinations prefixed with "/topic").
     * <p>
     * 2.设置服务端的prefix路径,加上MessageMapping路径组成接受消息的路径,也即是js发送的路径,然后把消息广播出去
     * Configure one or more prefixes to filter destinations targeting application annotated
     * methods. For example destinations prefixed with "/app" may be processed by annotated methods
     * while other destinations may target the message broker (e.g. "/topic", "/queue").
     * <p>
     * 设置两个路径,controller中配置topic群发, queue点对点
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setApplicationDestinationPrefixes("/app");
    }

    /**
     * 前端js通过chat路径与服务端建立websocket链接
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // Register a STOMP over WebSocket endpoint at the given mapping path.
        registry.addEndpoint("/chat").withSockJS();
    }
}
