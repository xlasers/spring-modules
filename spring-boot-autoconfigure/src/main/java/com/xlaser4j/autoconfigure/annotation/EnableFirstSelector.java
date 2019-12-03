package com.xlaser4j.autoconfigure.annotation;

import java.lang.annotation.*;

import org.springframework.context.annotation.Import;

/**
 * @package: com.xlaser4j.autoconfigure.annotation
 * @author: Elijah.D
 * @time: 2019/10/26 17:29
 * @description: 测试selector
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(FirstConfigurationSelector.class)
public @interface EnableFirstSelector {
    /**
     * 测试选择属性
     *
     * @return
     */
    ActiveMode mode() default ActiveMode.FIRST;
}
