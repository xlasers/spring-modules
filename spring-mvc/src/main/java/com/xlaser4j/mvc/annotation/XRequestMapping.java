package com.xlaser4j.mvc.annotation;

import java.lang.annotation.*;

/**
 * The interface X request mapping.
 *
 * @package: com.xlaser4j.spring.annotation
 * @author: Elijah.D
 * @time: 2018/9/19 10:04
 * @description: 自定义RequestMapping访问路径映射
 * @copyright: Copyright(c) 2018
 * @version: V1.0
 * @modified: Elijah.D
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface XRequestMapping {
    /**
     * Value string: url 默认为空 !
     *
     * @return the string
     */
    String value() default "";
}
