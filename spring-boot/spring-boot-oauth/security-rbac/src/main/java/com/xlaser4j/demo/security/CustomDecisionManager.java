package com.xlaser4j.demo.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * @package: com.xlaser4j.demo.security
 * @author: Elijah.D
 * @time: 2020/2/3 13:44
 * @description:
 * @modified: Elijah.D
 */
@Component
public class CustomDecisionManager implements AccessDecisionManager {
    /**
     * 核心逻辑:取出filter返回的角色名与当前登陆用户的角色集合做对比,如果包含则认为有权限
     * <p>
     * hierarchy:角色继承本质上就是将小的角色名字加入到大的角色集合里:eg admin > dev
     * admin.getAuthorities = [admin,dev]
     * <p>
     * 如果url需要两个角色,用户只需要有任意一个即可访问,此外若想用户必须有两个,则如下判断逻辑需要做出对应的修改
     *
     * @param auth       登陆user权限信息
     * @param o          {@link org.springframework.security.web.FilterInvocation}
     * @param collection Filter处理后返回本次url请求所需要的角色名(若不需要权限控制,则是自定义的标记:anyone_login)
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication auth, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute attribute : collection) {
            String requestRoleName = attribute.getAttribute();
            // 数据库中没有匹配到这个url,filter返回自定义attribute,因此自定义处理逻辑,这里假设只需要登陆即可访问
            if ("anyone_login".equals(requestRoleName)) {
                if (auth instanceof UsernamePasswordAuthenticationToken) {
                    // 登陆即可访问,结束验证
                    return;
                }
                if (auth instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("请登录访问,非法请求!");
                }
            }
            // 数据库匹配到url,filter返回所需要的role name,取出用户的role做对比即可
            List<String> userRoleNames = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            if (userRoleNames.contains(requestRoleName)) {
                return;
            }
        }
        throw new AccessDeniedException("权限不足!");
    }

    /**
     * 不做处理,放过
     *
     * @param configAttribute
     * @return
     */
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    /**
     * 不做处理,放过
     *
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
