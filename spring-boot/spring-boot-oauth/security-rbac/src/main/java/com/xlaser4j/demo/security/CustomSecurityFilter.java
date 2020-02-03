package com.xlaser4j.demo.security;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.xlaser4j.demo.entity.Menu;
import com.xlaser4j.demo.entity.Role;
import com.xlaser4j.demo.service.MenuServiceImpl;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

/**
 * @package: com.xlaser4j.demo.security
 * @author: Elijah.D
 * @time: 2020/2/3 13:43
 * @description:
 * @modified: Elijah.D
 */
@Component
public class CustomSecurityFilter implements FilterInvocationSecurityMetadataSource {
    private final MenuServiceImpl service;

    public CustomSecurityFilter(MenuServiceImpl service) {
        this.service = service;
    }

    /**
     * antMatcher:数据库url路径难以用正则匹配,通常采用ant形式的通配符{@link org.springframework.util.AntPathMatcher}
     * <p>
     * 核心逻辑处理: 拦截到url,根据url查询数据库中对应的数据,取出url对应的角色名(ROLE_..)
     * <p>
     * 如果数据库没有匹配到url,则返回自定义标识,后续单独处理eg:(ROLE_anyLogin)
     *
     * @param o {@link org.springframework.security.web.FilterInvocation}
     * @return 所需要的角色名
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        // 获取请求的url&数据库权限集合
        String requestUrl = ((FilterInvocation)o).getRequestUrl();
        List<Menu> menus = service.listMenus();

        // ant匹配,如果数据库能匹配到,则返回对应所需要的角色,如果没有匹配到,则认为这个url无需权限控制任意访问,自定义标记,后续处理
        AntPathMatcher matcher = new AntPathMatcher();
        Optional<Menu> first = menus.stream().filter(menu -> matcher.match(menu.getPattern(), requestUrl)).findFirst();
        if (first.isPresent()) {
            String[] roleNames = first.get().getRoles().stream().map(Role::getName).toArray(String[]::new);
            return SecurityConfig.createList(roleNames);
        } else {
            return SecurityConfig.createList("anyone_login");
        }
    }

    /**
     * 暂时用不到
     *
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 支持所有
     *
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
