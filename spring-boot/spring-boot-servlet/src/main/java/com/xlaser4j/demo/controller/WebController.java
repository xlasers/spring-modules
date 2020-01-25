package com.xlaser4j.demo.controller;

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
public class WebController {
    /**
     * test web
     *
     * @return
     */
    @GetMapping("/web")
    public String web() {
        return "Hello Web Basic Component!";
    }

    /**
     * test filter
     *
     * @return
     */
    @GetMapping("/filter")
    public String filter() {
        return "Hello Web Filter!";
    }
}
