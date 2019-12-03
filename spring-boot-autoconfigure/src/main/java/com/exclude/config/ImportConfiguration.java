package com.exclude.config;

import cn.hutool.core.lang.Console;
import com.xlaser4j.autoconfigure.annotation.EnableImport;
import org.springframework.context.annotation.Configuration;

/**
 * 测试spring-boot-application扫描非同一包下的配置失败
 *
 * @package: com.exclude.config
 * @author: Elijah.D
 * @time: 2019/10/27 10:38
 * @description: 通过 {@link EnableImport}注解实现转配
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@Configuration
public class ImportConfiguration {
    public ImportConfiguration() {
        Console.log("\n=============>>【ImportConfiguration】\n");
    }
}
