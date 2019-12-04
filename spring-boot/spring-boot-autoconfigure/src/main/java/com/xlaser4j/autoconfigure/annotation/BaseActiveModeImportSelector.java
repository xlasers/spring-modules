package com.xlaser4j.autoconfigure.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;

import cn.hutool.core.util.TypeUtil;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @param <A> 包含{@linkplain #DEFAULT_ACTIVE_MODE_ATTRIBUTE_NAME AdviceMode属性的注解}
 * @package: com.xlaser4j.autoconfigure.annotation
 * @author: Elijah.D
 * @time: 2019/10/27 14:23
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
public abstract class BaseActiveModeImportSelector<A extends Annotation> implements ImportSelector {
    /**
     * 自定义{@linkplain EnableFirstSelector, EnableSecondSelector}模式属性名,默认设置FIRST
     */
    private static final String DEFAULT_ACTIVE_MODE_ATTRIBUTE_NAME = "mode";

    /**
     * 根据注解中的mode装配对应的配置
     *
     * @param metadata 注解元信息
     * @return 需要装配的配置类名称
     * @see Object#getClass() 获取运行时类的信息(这里获取用来子类)
     */
    @Override
    public final String[] selectImports(AnnotationMetadata metadata) {

        // 获取子类泛型类型(父类中可以通过getClass()动态获取运行时的子类)
        Type typeArgument = TypeUtil.getTypeArgument(getClass());

        // 获取注解属性值
        Map<String, Object> attributes = metadata.getAnnotationAttributes(typeArgument.getTypeName());
        assert attributes != null;
        ActiveMode activeMode = (ActiveMode)attributes.get(DEFAULT_ACTIVE_MODE_ATTRIBUTE_NAME);

        // 根据类型获取配置
        String[] imports = selectImports(activeMode);
        if (imports == null) {
            throw new IllegalArgumentException("Unknown ActiveMode: " + activeMode);
        }

        return imports;
    }

    /**
     * 根据类型实际选择装配类名
     *
     * @param activeMode mode
     * @return 装配类名
     */
    protected abstract String[] selectImports(ActiveMode activeMode);
}
