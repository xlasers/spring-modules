package com.xlaser4j.autoconfigure.annotation;

import java.lang.annotation.*;

import com.exclude.config.ImportConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @package: com.xlaser4j.autoconfigure.annotation
 * @author: Elijah.D
 * @time: 2019/10/26 17:29
 * @description: 测试import
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ImportConfiguration.class)
public @interface EnableImport {
}
