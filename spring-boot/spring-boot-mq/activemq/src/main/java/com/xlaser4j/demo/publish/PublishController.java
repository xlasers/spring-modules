package com.xlaser4j.demo.publish;

import java.util.Date;

import javax.jms.Queue;

import com.xlaser4j.demo.model.Msg;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.demo.publish
 * @author: Elijah.D
 * @time: 2020/2/5 15:49
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class PublishController {
    private final JmsMessagingTemplate template;

    private final Queue objectQueue;

    private final Queue stringQueue;

    public PublishController(JmsMessagingTemplate template, Queue objectQueue, Queue stringQueue) {
        this.template = template;
        this.objectQueue = objectQueue;
        this.stringQueue = stringQueue;
    }

    /**
     * string: 发布消息,订阅者自动接收
     */
    @GetMapping("/s")
    public String publishString() {
        template.convertAndSend(stringQueue, "Test send string msg!");
        return "String-消息发送成功: 查看控制台subscribe输出内容!";
    }

    /**
     * object: 发布消息,订阅者自动接收
     * <p>
     * ClassNotFoundException:This class is not trusted to be serialized as ObjectMessage payload
     * 发送对象时,yml中必须配置trust-all=true,表示接收对象类型的数据,同时对象必须实现序列化接口,否则抛出异常MessageConversionException
     */
    @GetMapping("/o")
    public String publishObject() {
        template.convertAndSend(objectQueue, new Msg("Test", new Date(), "Test send object msg!"));
        return "Object-消息发送成功(trust-all必须为true): 查看控制台subscribe输出内容!";
    }
}
