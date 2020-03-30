package com.xlaser4j.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

/**
 * @package: com.xlaser4j.zuul.filter
 * @author: Elijah.D
 * @time: 2020/3/31 14:27
 * @description:
 * @modified: Elijah.D
 */
@Component
public class CustomZuulFilter extends ZuulFilter {
    /**
     * Standard types in Zuul are "pre" for pre-routing filtering, "route" for routing to an origin,
     * "post" for post-routing filters, "error" for error handling. We also support a "static" type
     * for static responses see  StaticResponseFilter.
     *
     * @return 通常使用pre
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * a "true" return from this method means that the run() method should be invoked
     *
     * @return true
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 核心逻辑
     * <p>
     * runFilter checks !isFilterDisabled() and shouldFilter(). The run() method is invoked if both are true.
     *
     * @return return from ZuulFilterResult
     */
    @Override
    public Object run() {

        //  获取信息,核心逻辑处理
        RequestContext context = RequestContext.getCurrentContext();
        System.out.println("Pre Filter正在过滤处理请求: " + context.getRequest().getRequestURI());

        // 模拟登陆信息
        String admin = "admin";
        HttpServletRequest request = context.getRequest();
        String username = request.getParameter("username");
        if (!admin.equals(username)) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            context.addZuulResponseHeader("content-type", "text/html;charset=utf-8");
            context.setResponseBody("权限不足,请联系管理员处理: 测试zuul的preFilter,加上参数username=admin即可!");
        }

        return "Test Pre Filter";
    }
}
