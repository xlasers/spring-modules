package com.xlaser4j.demo.subscribe;

import com.xlaser4j.demo.model.Msg;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @package: com.xlaser4j.demo.subscribe
 * @author: Elijah.D
 * @time: 2020/2/5 15:55
 * @description:
 * @modified: Elijah.D
 */
@Component
public class Subscriber {
    /**
     * 订阅者,订阅的queue
     * <p>
     * 可以另起一个服务测试两个服务间的通讯
     */
    @JmsListener(destination = "string-queue")
    public void subscribe(String msg) {
        System.out.println("String-通信成功: " + msg);
    }

    /**
     * 订阅者,订阅的queue
     * <p>
     * ClassNotFoundException:This class is not trusted to be serialized as ObjectMessage payload
     * 发送对象时,yml中必须配置trust-all=true,表示接收对象类型的数据,同时对象必须实现序列化接口,否则抛出异常MessageConversionException
     */
    @JmsListener(destination = "object-queue")
    public void subscribe(Msg msg) {
        System.out.println("Object-通信成功: " + msg);
    }
}
