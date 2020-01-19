package com.xlaser4j.advice.advice;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @package: com.xlaser4j.advice.advice
 * @author: Elijah.D
 * @time: 2020/1/19 13:50
 * @description: 请求参数预处理
 * @modified: Elijah.D
 */
@ControllerAdvice
public class ApiDataBinder {
    /**
     * 所有带有"book."前缀的参数,自动绑定到Book(b)参数
     *
     * @param binder
     */
    @InitBinder(value = "b")
    public void initBook(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("book.");
    }

    /**
     * 所有带有"author."前缀的参数,自动绑定到Author(a)参数
     *
     * @param binder
     */
    @InitBinder(value = "a")
    public void initAuthor(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("author.");
    }
}
