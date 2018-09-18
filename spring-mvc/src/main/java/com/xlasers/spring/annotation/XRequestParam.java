package com.xlasers.spring.annotation;

import java.lang.annotation.*;

/**
 * The interface X request param.
 *
 * @package: com.xlasers.spring.annotation
 * @author: Elijah.D
 * @time: CreateAt 2018/9/19 && 10:05
 * @description: 自定义RequestParam参数注解
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
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
