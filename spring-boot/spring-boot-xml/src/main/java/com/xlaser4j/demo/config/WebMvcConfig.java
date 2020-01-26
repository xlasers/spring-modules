package com.xlaser4j.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/1/26 15:19
 * @description:
 * @modified: Elijah.D
 */
@Configuration
@ImportResource("classpath:custom-bean.xml")
public class WebMvcConfig {
}
