package com.xlaser4j.autoconfigure.annotation;

import java.util.List;
import java.util.Map;

import cn.hutool.core.collection.CollUtil;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;

/**
 * @package: com.xlaser4j.autoconfigure.config
 * @author: Elijah.D
 * @time: 2019/10/27 16:23
 * @description: 测试Condition条件注入
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@Configuration
public class OnHobbyCondition implements Condition {
    private static final List<HobbyType> BACKEND_HOBBY = CollUtil.newArrayList(HobbyType.JAVA, HobbyType.PYTHON);

    /**
     * 自定义{@link ConditionalOnHobby}模式属性名,默认设置FIRST
     */
    private static final String DEFAULT_HOBBY_ATTRIBUTE_NAME = "hobby";

    /**
     * 决定是否自动装配bean
     *
     * @param context  关于{@code Condition}的全局信息
     * @param metadata metadata of the {@link AnnotationMetadata class} or {@link MethodMetadata method} being checked
     * @return component  {@code true} can be registered,{@code false} can not be registered
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        // 获取注解属性值
        Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionalOnHobby.class.getName());
        assert attributes != null;
        HobbyType hobby = (HobbyType)attributes.get(DEFAULT_HOBBY_ATTRIBUTE_NAME);

        // true/false
        return BACKEND_HOBBY.contains(hobby);
    }
}
