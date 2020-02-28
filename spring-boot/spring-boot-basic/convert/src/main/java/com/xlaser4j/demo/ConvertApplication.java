package com.xlaser4j.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {@link org.springframework.core.convert.converter.Converter}
 *
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/0/0 00:00
 * @description: Start App Template
 * @modified: Elijah.D
 */
@SpringBootApplication
public class ConvertApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================Spring中提供了Convert类,可以实现参数的转换,eg:String转Date==============================\n");
        SpringApplication.run(ConvertApplication.class, args);
    }
}