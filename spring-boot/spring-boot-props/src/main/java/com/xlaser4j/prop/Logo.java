package com.xlaser4j.prop;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @package: com.xlaser4j.prop
 * @author: Elijah.D
 * @time: 2019/12/5 12:29
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@Data
@Component
public class Logo {
    @Value("${company.logo}")
    private String logo;
}
