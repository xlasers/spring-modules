package com.xlaser4j.feign.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.xlaser4j.feign.remote.ExtendProviderRemote;
import com.xlaser4j.feign.remote.SimpleProviderRemote;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.feign.controller
 * @author: Elijah.D
 * @time: 2020/3/26 13:55
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class ConsumerController {
    private final SimpleProviderRemote simpleRemote;

    private final ExtendProviderRemote extendRemote;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ConsumerController(SimpleProviderRemote simpleRemote, ExtendProviderRemote extendRemote) {
        this.simpleRemote = simpleRemote;
        this.extendRemote = extendRemote;
    }

    @GetMapping("/cs")
    public String simpleTest() {
        return simpleRemote.simpleTest();
    }

    /**
     * 测试feign,带参数
     *
     * @return msg
     */
    @GetMapping("/cp")
    public String simpleWithParam() {
        return simpleRemote.simpleWithParam("name");
    }

    /**
     * 测试feign,带body
     *
     * @return msg
     */
    @GetMapping("/cb")
    public String simpleWithBody() {
        return simpleRemote.simpleWithBody(new Integer[]{1, 2, 3});
    }

    /**
     * 测试feign,带参数: header
     * <p>
     * 注意header参数的入参encode/接参decode
     *
     * @return msg
     */
    @GetMapping("/ch")
    public String simpleWithHeader() throws UnsupportedEncodingException {
        String chinese = "注意http利用header传参中文乱码问题(不针对feign),入参需要encode编码,接收方需要decode解码!";
        return simpleRemote.simpleWithHeader(URLEncoder.encode(chinese, "UTF-8"));
    }

    /**
     * 测试feign的继承特性
     *
     * @return msg
     */
    @GetMapping("/e")
    public String commonGet() {
        return extendRemote.commonGet();
    }

    /**
     * 测试feign的继承特性
     *
     * @return msg
     */
    @GetMapping("/ep")
    public String commonGetWithParam() {
        return extendRemote.commonGetWithParam("key");
    }

    /**
     * 测试feign的继承特性
     *
     * @return msg
     */
    @GetMapping("/eb")
    public String commonGetWithBody() {
        return extendRemote.commonGetWithBody(new String[]{"a", "b", "c"});
    }
}
