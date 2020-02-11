package com.xlaser4j.demo.annotation;

import java.lang.annotation.*;

/**
 * @package: com.xlaser4j.demo.annotation
 * @author: Elijah.D
 * @time: 2018/9/19 10:05
 * @description: 自定义RequestParam参数注解
 * @modified: Elijah.D
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface XRequestParam {
    /**
     * Value string: 参数
     *
     * @return the string
     */
    String value();
}
