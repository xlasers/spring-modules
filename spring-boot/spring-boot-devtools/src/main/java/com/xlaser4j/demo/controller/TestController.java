package com.xlaser4j.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/2/1 13:21
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class TestController {
    /**
     * 1.idea中设置自动编译实现devtools热部署-Compile: Build Project Automatically
     *
     * @return
     */
    @GetMapping("/a")
    public String testA() {
        return "Idea-Compile: Build Proasdfasfjecasdft Automaticallyadsfasdasfsa";
    }

    /**
     * 2.idea中设置自动编译实现devtools热部署-Registry: Ctrl+Shift+Alt+/
     *
     * @return
     */
    @GetMapping("/b")
    public String testB() {
        return "Idea-Registry: Ctrl+Shift+Alt+/";
    }
}
