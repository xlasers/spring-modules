package com.xlaser4j.autoconfigure.config;

import cn.hutool.core.lang.Console;
import org.springframework.context.annotation.Configuration;

/**
 * @package: com.xlaser4j.autoconfigure.config
 * @author: Elijah.D
 * @time: 2019/10/27 10:38
 * @description: 测试spring-boot-application自动扫描统一包下的配置
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@Configuration
public class BootConfiguration {
    public BootConfiguration() {
        Console.log("\n=============>>【BootConfiguration】\n");
    }
}
