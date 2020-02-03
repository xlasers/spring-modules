package com.xlaser4j.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/2/2 20:04
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class UserController {
    /**
     * 测试只有admin角色的用户可以访问
     *
     * @return
     */
    @GetMapping("/admin/user")
    public String testAdmin() {
        return "Hello Admin: 测试只有admin角色的用户可以访问!";
    }

    /**
     * hierarchy:由于权限继承关系,虽然设置了只能dev访问,但是大于dev的权限也可以访问
     * <p>
     * 测试大于dev角色的用户可以访问
     *
     * @return
     */
    @GetMapping("/dev/user")
    public String testDev() {
        return "Hello Dev: 测试大于dev角色的用户可以访问!";
    }

    /**
     * hierarchy:由于权限继承关系,虽然设置了只能user访问,但是大于user的权限也可以访问
     * <p>
     * 测试大于user角色的用户可以访问
     *
     * @return
     */
    @GetMapping("/user/user")
    public String testUser() {
        return "Hello User: 测试大于user角色的用户可以访问!";
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
