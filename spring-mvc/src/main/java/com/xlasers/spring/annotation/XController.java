package com.xlasers.spring.annotation;

import java.lang.annotation.*;

/**
 * The interface X controller.
 *
 * @package: com.xlasers.spring.annotation
 * @author: Elijah.D
 * @time: CreateAt 2018/9/19 && 10:02
 * @description: 自定义controller注解
 * @copyright: Copyright © 2018 xlasers
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
