package com.xlaser4j.demo.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/1/25 22:40
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class ConvertController {
    /**
     * test thymeleaf template
     *
     * @return
     */
    @GetMapping("/test")
    public String test(Date start) {
        System.out.println(start);
        return "Test Convert String To Date!";
    }
}
