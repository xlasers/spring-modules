package com.xlaser4j.demo.annotation;

import java.lang.annotation.*;

/**
 *
 * @package: com.xlaser4j.demo.annotation
 * @author: Elijah.D
 * @time: 2018/9/19 10:04
 * @description: 自定义RequestMapping访问路径映射
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
