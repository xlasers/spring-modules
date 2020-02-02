package com.xlaser4j.demo.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @package: com.xlaser4j.demo.service
 * @author: Elijah.D
 * @time: 2020/2/2 15:57
 * @description:
 * @modified: Elijah.D
 */
@Service
public class TestService {
    /**
     * @return
     */
    @PreAuthorize("hasRole('user')")
    public String preU() {
        return "PreAuthorize: 首先配置config类开启方法权限控制,然后方法上添加PreAuthorize注解控制权限(user).";
    }

    /**
     * @return
     */
    @PreAuthorize("hasAnyRole('user','admin')")
    public String preAu() {
        return "PreAuthorize: 首先配置config类开启方法权限控制,然后方法上添加PreAuthorize注解控制权限(user&admin).";
    }

    /**
     * 查看登陆成功的认证信息即可直到前缀信息: authority: "ROLE_user"
     * {
     * password: null,
     * username: "elijah",
     * authorities: [{
     * authority: "ROLE_user"
     * }],
     * accountNonExpired: true,
     * accountNonLocked: true,
     * credentialsNonExpired: true,
     * enabled: true
     * }
     *
     * @return
     */
    @Secured("ROLE_admin")
    public String secureA() {
        return "Secured: 首先配置config类开启方法权限控制,然后(默认角色前缀ROLE_)方法上添加Secured注解控制权限(admin).";
    }

    /**
     * @return
     */
    @Secured({"ROLE_admin", "ROLE_user"})
    public String secureAu() {
        return "Secured: 首先配置config类开启方法权限控制,然后默认角色前缀ROLE_方法上添加Secured注解控制权限(admin&user).";
    }
}
