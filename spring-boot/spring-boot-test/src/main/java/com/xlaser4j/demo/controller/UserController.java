package com.xlaser4j.demo.controller;

import com.xlaser4j.demo.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/2/1 17:14
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class UserController {
    /**
     * test get
     *
     * @param name
     * @return
     */
    @GetMapping("/a")
    public String getName(String name) {
        return "Test " + name;
    }

    /**
     * test post
     *
     * @param user
     * @return
     */
    @PostMapping("/b")
    public User saveUser(@RequestBody User user) {
        return user;
    }
}
