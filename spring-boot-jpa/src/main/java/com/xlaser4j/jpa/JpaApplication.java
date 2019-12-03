package com.xlaser4j.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Jpa application.
 *
 * @package: com.xlaser4j.spring
 * @author: Elijah.D
 * @time: 2018/10/6 13:59
 * @description: app主类
 * @copyright: Copyright(c) 2018
 * @version: V1.0
 * @modified: Elijah.D
 */
@SpringBootApplication
public class JpaApplication {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }
}
