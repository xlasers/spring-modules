package com.xlasers.spring.annotation;

import java.lang.annotation.*;

/**
 * The interface Data source.
 *
 * @package: com.xlasers.spring.annotation
 * @author: Elijah.D
 * @time: CreateAt 2018/10/3 && 11:04
 * @description: 多数据源注解
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
	/**
	 * Name string.
	 *
	 * @return the string
	 */
	String name() default "";
}
