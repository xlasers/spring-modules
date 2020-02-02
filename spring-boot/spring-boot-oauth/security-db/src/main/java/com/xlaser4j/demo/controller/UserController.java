package com.xlaser4j.demo.controller;

import com.xlaser4j.demo.service.UserServiceImpl;
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
    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
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
     * hierarchy:由于权限继承关系,虽然设置了只能dev访问,但是大于dev的权限也可以访问
     * <p>
     * 测试大于dev角色的用户可以访问
     *
     * @return
     */
    @GetMapping("/d/user")
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
    @GetMapping("/u/user")
    public String testUser() {
        return "Hello User: 测试大于user角色的用户可以访问!";
    }

    /**
     * hierarchy:由于权限继承关系,虽然设置了只能User/Admin访问,但是大于user的权限也可以访问
     * <p>
     * 测试User/Admin角色的用户可以访问,但是dev大于user,也可以访问
     *
     * @return
     */
    @GetMapping("/au/user")
    public String testUserAndAdmin() {
        return "Hello UserAndAdmin: 测试User/Admin角色的用户可以访问,但是dev大于user,也可以访问!";
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
