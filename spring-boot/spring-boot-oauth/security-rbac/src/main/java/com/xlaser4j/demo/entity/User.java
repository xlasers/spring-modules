package com.xlaser4j.demo.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 注意{@link UserDetails}中部分方法等价于user的get方法,所以这里省去get方法,否则报错ambiguous getter method.
 *
 * @package: com.xlaser4j.demo.model
 * @author: Elijah.D
 * @time: 2020/2/2 19:34
 * @description:
 * @modified: Elijah.D
 */
@Setter
@ToString
public class User implements UserDetails {
    private static final long serialVersionUID = 6411864551557783203L;

    private Integer id;

    private String password;

    private String username;

    private Boolean expired;

    private Boolean locked;

    private Boolean enabled;

    private List<Role> roles;

    public Integer getId() {
        return id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    /**
     * 用户的角色name的集合{@link SimpleGrantedAuthority}注意默认前缀ROLE_,如果数据库没有添加,这里需要额外添加处理
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    /**
     * 数据库暂时没有对应字段,这里直接设置为true,没有过期,放过这个验证
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
