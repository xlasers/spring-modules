package com.xlaser4j.demo;

import java.util.List;
import java.util.Map;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2019/12/5 10:29
 * @description: 通过properties注入数据
 * @modified: Elijah.D
 */
@Data
@Component
@PropertySource("classpath:prop/company.properties")
@ConfigurationProperties(prefix = "company")
public class Company {
    private String name;

    private String location;

    private String[] weekday;

    private List<String> weekend;

    private Map<String, String> sex;

    private List<Map<String, String>> employees;
}
