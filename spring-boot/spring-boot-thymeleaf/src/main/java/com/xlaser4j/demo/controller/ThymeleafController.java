package com.xlaser4j.demo.controller;

import com.xlaser4j.demo.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/1/25 22:40
 * @description:
 * @modified: Elijah.D
 */
@Controller
public class ThymeleafController {
    /**
     * test thymeleaf template
     *
     * @return
     */
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Test Thymeleaf Template!";
    }

    /**
     * test thymeleaf template
     * <p>
     * 返回视图名称,不能使用ResponseBody/RestController
     *
     * @return
     */
    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("persons", Person.listPersons());
        return "thymeleaf";
    }
}
