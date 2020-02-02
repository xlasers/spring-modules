package com.xlaser4j.demo.service;

import java.util.List;

import com.xlaser4j.demo.entity.Role;
import com.xlaser4j.demo.entity.User;
import com.xlaser4j.demo.mapper.UserMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @package: com.xlaser4j.demo.service
 * @author: Elijah.D
 * @time: 2020/2/2 20:04
 * @description:
 * @modified: Elijah.D
 */
@Service
public class UserServiceImpl implements UserDetailsService {
    private final UserMapper mapper;

    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 根据用户名从数据库查询出用户相关信息,security自动做账号密码权限的匹配工作.
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // CHECK USER
        User user = mapper.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + ": 用户不存在!");
        }

        // SET ROLE
        List<Role> roles = mapper.listRolesByUserId(user.getId());
        user.setRoles(roles);

        // SECURITY AUTO CHECK PERMISSIONS
        return user;
    }

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
