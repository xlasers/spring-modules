package com.xlaser4j.demo.annotation;

import java.lang.annotation.*;

/**
 * @package: com.xlaser4j.demo.annotation
 * @author: Elijah.D
 * @time: 2018/9/19 10:02
 * @description: 自定义controller注解
 * @modified: Elijah.D
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface XController {
    /**
     * Value string 默认为空 !
     *
     * @return the string
     */
    String value() default "";
}
