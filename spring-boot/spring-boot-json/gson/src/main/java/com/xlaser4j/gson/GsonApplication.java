package com.xlaser4j.gson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {@link org.springframework.http.converter.HttpMessageConverter}
 * Strategy interface that specifies a converter that can convert from and to HTTP requests and responses
 *
 * @package: com.xlaser4j.gson
 * @author: Elijah.D
 * @time: 2020/0/0 00:00
 * @description: Start App Template
 * @modified: Elijah.D
 */
@SpringBootApplication
public class GsonApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================Json数据的处理涉及到反序列化成对象,或者把对象序列化成json字符串,都是由HttpMessageConverter实现==============================\n");
        System.out.println("\n==============================[jackson/gson/fastJson都是实现了HttpMessageConverter]==============================\n");
        System.out.println("\n==============================springboot中默认配置了Gson,只需要添加依赖就可以实现参数的序列化/反序列化==============================\n");
        SpringApplication.run(GsonApplication.class, args);
    }
}
