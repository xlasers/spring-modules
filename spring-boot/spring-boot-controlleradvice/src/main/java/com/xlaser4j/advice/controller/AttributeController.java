package com.xlaser4j.advice.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.advice.controller
 * @author: Elijah.D
 * @time: 2020/1/19 13:35
 * @description: controller中访问全局参数
 * @modified: Elijah.D
 */
@RestController
public class AttributeController {
    /**
     * 测试全局参数
     */
    @GetMapping("/a")
    public String testModelAttribute(Model model) {

        System.out.println(model.asMap().get("map"));
        System.out.println(model.asMap().get("list"));

        return "Hello Attributes";
    }
}
