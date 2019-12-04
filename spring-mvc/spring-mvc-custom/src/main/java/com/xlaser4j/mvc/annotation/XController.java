package com.xlaser4j.mvc.annotation;

import java.lang.annotation.*;

/**
 * The interface X controller.
 *
 * @package: com.xlaser4j.spring.annotation
 * @author: Elijah.D
 * @time: 2018/9/19 10:02
 * @description: 自定义controller注解
 * @copyright: Copyright(c) 2018
 * @version: V1.0
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
