package com.xlaser4j.mvc.annotation;

import java.lang.annotation.*;

/**
 * The interface X request param.
 *
 * @package: com.xlaser4j.spring.annotation
 * @author: Elijah.D
 * @time: 2018/9/19 10:05
 * @description: 自定义RequestParam参数注解
 * @copyright: Copyright(c) 2018
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
