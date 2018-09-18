package com.xlasers.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Redis application.
 *
 * @package: com.xlasers.spring
 * @author: Elijah.D
 * @time: CreateAt 2018/9/28 && 9:49
 * @description: App
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@SpringBootApplication
public class RedisApplication
{
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        new SpringApplication(RedisApplication.class).run(args);

    }
}
