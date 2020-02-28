package com.xlaser4j.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/1/26 14:31
 * @description:
 * @modified: Elijah.D
 */
@Controller
public class WelcomeController {
    /**
     * 自定义欢迎页,使用thymeleaf动态模板,当静态目录下没有index文件时候,将调用这个方法,跳转到templates下寻找.
     *
     * @return
     */
    @GetMapping("/index")
    public String test() {
        return "index";
    }
}
