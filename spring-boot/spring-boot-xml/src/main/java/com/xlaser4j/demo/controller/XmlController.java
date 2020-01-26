package com.xlaser4j.demo.controller;

import com.xlaser4j.demo.component.XmlComponent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/1/26 15:24
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class XmlController {
    private final XmlComponent component;

    public XmlController(XmlComponent component) {
        this.component = component;
    }

    @GetMapping("/a")
    public String test() {
        return component.component();
    }
}
