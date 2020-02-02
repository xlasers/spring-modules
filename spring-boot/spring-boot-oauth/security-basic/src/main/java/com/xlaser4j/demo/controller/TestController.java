package com.xlaser4j.demo.controller;

import com.xlaser4j.demo.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/2/2 14:03
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class TestController {
    private final TestService service;

    public TestController(TestService service) {
        this.service = service;
    }

    /**
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
     * 测试User/Admin角色的用户可以访问
     *
     * @return
     */
    @GetMapping("/au/user")
    public String testUserAndAdmin() {
        return "Hello UserAndAdmin: 测试只有UserAndAdmin角色的用户可以访问!";
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

    /**
     * 测试登陆即可访问url:
     * 内部方法通过{@link org.springframework.security.access.prepost.PreAuthorize}做了权限控制.
     *
     * @return
     */
    @GetMapping("/pre/u")
    public String testPreU() {
        return service.preU();
    }

    /**
     * 测试登陆即可访问url:
     * 内部方法通过{@link org.springframework.security.access.prepost.PreAuthorize}做了权限控制.
     *
     * @return
     */
    @GetMapping("/pre/au")
    public String testPreAu() {
        return service.preAu();
    }

    /**
     * 测试登陆即可访问url:
     * 内部方法通过{@link org.springframework.security.access.annotation.Secured}做了权限控制.
     *
     * @return
     */
    @GetMapping("/se/a")
    public String tbsSecureA() {
        return service.secureA();
    }

    /**
     * 测试登陆即可访问url:
     * 内部方法通过{@link org.springframework.security.access.annotation.Secured}做了权限控制.
     *
     * @return
     */
    @GetMapping("/se/au")
    public String tbsSecureAu() {
        return service.secureAu();
    }
}
