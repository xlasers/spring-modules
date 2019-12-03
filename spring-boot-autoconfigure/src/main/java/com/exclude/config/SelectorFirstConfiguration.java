package com.exclude.config;

import cn.hutool.core.lang.Console;
import com.xlaser4j.autoconfigure.annotation.EnableFirstSelector;
import org.springframework.context.annotation.Configuration;

/**
 * 测试spring-boot-application扫描非同一包下的配置失败
 *
 * @package: com.meta.config
 * @author: Elijah.D
 * @time: 2019/10/26 17:30
 * @description: 通过 {@link EnableFirstSelector}注解实现装配
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@Configuration
public class SelectorFirstConfiguration {
    public SelectorFirstConfiguration() {
        Console.log("\n=============>>【SelectorFirstConfiguration】\n");
    }
}
