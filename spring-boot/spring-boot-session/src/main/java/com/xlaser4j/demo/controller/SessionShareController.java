package com.xlaser4j.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/1/30 18:45
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class SessionShareController {
    /**
     * 打jar包,运行两个端口服务,引入security,一个登陆set,另外一个get不需要再次登陆,验证session共享成功.
     */
    @Value("${server.port}")
    private Integer port;

    /**
     * set session
     * <p>
     * 8080端口访问,security拦截需要登陆
     */
    @GetMapping("/s")
    public Integer setSession(HttpSession session) {
        session.setAttribute("key", "value" + System.currentTimeMillis());
        return port;
    }

    /**
     * get session
     * <p>
     * 8081端口访问,security不需要登陆,因为set session已经认证
     */
    @GetMapping("/g")
    public String getSession(HttpSession session) {
        return session.getAttribute("key").toString() + port;
    }
}
