package com.xlaser4j.advice.controller;

import com.xlaser4j.advice.model.Author;
import com.xlaser4j.advice.model.Book;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.advice.controller
 * @author: Elijah.D
 * @time: 2020/1/19 13:35
 * @description: 预处理请求参数
 * @modified: Elijah.D
 */
@RestController
public class BinderController {
    /**
     * 测试绑定参数
     * <p>
     * 注意,这里故意设置book和author的参数都有name的情况下,默认是绑定失败的,通过ModelAttribute和InitBinder完成参数
     * 预处理,正确区分绑定参数值,当然还有一些额外的功能参考{@link org.springframework.web.bind.annotation.InitBinder}
     */
    @PostMapping("/b")
    public String testModelAttribute(@ModelAttribute("b") Book book, @ModelAttribute("a") Author author) {

        System.out.println(book);
        System.out.println(author);

        return "Hello Binder";
    }
}
