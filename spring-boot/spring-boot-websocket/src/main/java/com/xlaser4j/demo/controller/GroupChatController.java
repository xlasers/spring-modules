package com.xlaser4j.demo.controller;

import java.security.Principal;

import com.xlaser4j.demo.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/2/5 10:58
 * @description:
 * @modified: Elijah.D
 */
@Controller
public class GroupChatController {
    private final SimpMessagingTemplate template;

    public GroupChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    /**
     * 群发
     * <p>
     * 通过注解sendTo指明广播目的地
     *
     * @param message
     * @return
     */
    @MessageMapping("/annotation")
    @SendTo("/topic/group")
    public Message byAnnotation(Message message) {
        return message;
    }

    /**
     * 群发
     * <p>
     * 通过template指明广播目的地,并发送消息
     *
     * @param message
     * @return
     */
    @MessageMapping("/template")
    public void byTemplate(Message message) {
        template.convertAndSend("/topic/group", message);
    }

    /**
     * 点对点
     * <p>
     * 通过template指明广播目的地,并发送消息
     * <p>
     * toUser方法:点对点发送时,destination默认会加上前缀/user,所以前端js订阅路径中需要添加/user
     *
     * @param message
     * @return
     */
    @MessageMapping("/point")
    public void chatTotChat(Principal principal, Message message) {
        message.setFrom(principal.getName());
        template.convertAndSendToUser(message.getTo(), "/queue/one", message);
    }
}
