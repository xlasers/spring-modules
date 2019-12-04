package com.xlaser4j.autoconfigure.annotation;

import java.lang.annotation.*;

import org.springframework.context.annotation.Conditional;

/**
 * @package: com.xlaser4j.autoconfigure.annotation
 * @author: Elijah.D
 * @time: 2019/10/27 17:31
 * @description: 测试condition条件注解
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(OnHobbyCondition.class)
public @interface ConditionalOnHobby {
    /**
     * 根据hobby决策
     *
     * @return
     */
    HobbyType hobby() default HobbyType.JAVA;
}
