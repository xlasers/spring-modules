package com.xlaser4j.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @package: com.xlaser4j.feign
 * @author: Elijah.D
 * @time: 2020/3/26 11:27
 * @description:
 * @modified: Elijah.D
 */
@SpringBootApplication
public class FeignProviderApplication {
    public static void main(String[] args) {
        System.out.println("======================================= 由于netflix闭源问题,feign有springCloud团队开发继续维护并且更名为openfeign =======================================");
        SpringApplication.run(FeignProviderApplication.class);
    }
}
