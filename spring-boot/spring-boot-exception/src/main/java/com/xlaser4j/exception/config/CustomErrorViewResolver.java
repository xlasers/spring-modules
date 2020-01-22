package com.xlaser4j.exception.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 提供自定义异常数据类,代替默认resolver
 *
 * @package: com.xlaser4j.exception.config
 * @author: Elijah.D
 * @time: 2020/1/22 21:28
 * @description:
 * @modified: Elijah.D
 */
@Component
public class CustomErrorViewResolver extends DefaultErrorViewResolver {
    /**
     * Create a new {@link DefaultErrorViewResolver} instance.
     *
     * @param applicationContext the source application context
     * @param resourceProperties resource properties
     */
    public CustomErrorViewResolver(ApplicationContext applicationContext, ResourceProperties resourceProperties) {
        super(applicationContext, resourceProperties);
    }

    /**
     * 同样继承父类,修改返回的view信息,这里的model和{@link CustomErrorAttributes#getErrorAttributes(WebRequest, boolean)}
     * 数据一致,但是这里的model是unsupported modified的,所以这里想修改错误数据,只能创建一个map然后遍历model数据复制处理.
     * <p>
     * {@link DefaultErrorViewResolver#resolve(String, Map)},String errorViewName = "error/" + viewName; provider != null
     * 如果存在动态页面则直接使用templates下的error.
     * <p>
     * {@link DefaultErrorViewResolver#resolveResource(String, Map)},resourceProperties.getStaticLocations()
     * TemplateAvailabilityProvider不存在则使用locations中的四个静态路径下的error目录.
     *
     * @param request
     * @param status
     * @param model
     * @return
     */
    @SuppressWarnings("JavadocReference")
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        ModelAndView modelAndView = super.resolveErrorView(request, status, model);

        // 自定义设置处理,除404外使用custom-error的模板信息
        if (!HttpStatus.NOT_FOUND.equals(status)) {
            modelAndView.setViewName("custom-error");
        }

        return modelAndView;
    }
}
