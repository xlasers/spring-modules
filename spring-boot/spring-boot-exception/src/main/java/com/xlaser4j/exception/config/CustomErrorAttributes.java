package com.xlaser4j.exception.config;

import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

/**
 * 提供自定义异常数据类,代替默认attributes
 *
 * @package: com.xlaser4j.exception.config
 * @author: Elijah.D
 * @time: 2020/1/22 21:21
 * @description:
 * @modified: Elijah.D
 */
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
    /**
     * default已经对异常数据进行处理封装,所以继承之后,可以手动添加额外异常数据处理.
     *
     * @param webRequest
     * @param includeStackTrace
     * @return
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> attributes = super.getErrorAttributes(webRequest, includeStackTrace);
        System.out.println("\n==============================" + attributes + "==============================\n");

        attributes.put("customError", "This is my custom error msg!");
        return attributes;
    }
}
