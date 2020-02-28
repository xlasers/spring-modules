package com.xlaser4j.demo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2019/12/5 12:29
 * @description:
 * @modified: Elijah.D
 */
@Data
@Component
public class Logo {
    @Value("${company.logo}")
    private String logo;
}
