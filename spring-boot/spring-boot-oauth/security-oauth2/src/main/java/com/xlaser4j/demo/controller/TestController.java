package com.xlaser4j.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/2/4 17:21
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class TestController {
    /**
     * /**
     * 测试只有admin角色的用户可以访问
     *
     * @return
     */
    @GetMapping("/a/user")
    public String testAdmin() {
        return "Hello Admin: 测试只有admin角色的用户可以访问!";
    }

    /**
     * 测试只有user角色的用户可以访问
     *
     * @return
     */
    @GetMapping("/u/user")
    public String testUser() {
        return "Hello User: 测试只有user角色的用户可以访问!";
    }

    /**
     * 测试登陆即可访问
     *
     * @return
     */
    @GetMapping("/all/user")
    public String testLogin() {
        return "Hello Login: 测试登陆即可访问!";
    }
}
