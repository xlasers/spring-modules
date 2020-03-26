package com.xlaser4j.feign.hystrix;

import com.xlaser4j.feign.remote.SimpleProviderRemote;
import org.springframework.stereotype.Component;

/**
 * @package: com.xlaser4j.feign.hystrix
 * @author: Elijah.D
 * @time: 2020/3/26 14:50
 * @description: 服务降级1: 利用类实现
 * @modified: Elijah.D
 */
@Component("a")
@SuppressWarnings("SpringMVCViewInspection")
public class FallBackWithBean implements SimpleProviderRemote {
    @Override
    public String simpleTest() {
        return "simpleTest: 当出现异常时就会降级调用这里的方法![因为继承关系,如果导致url路径重复,或许需要加个任意基础路径,但是版本不一致或许不需要?]";
    }

    @Override
    public String simpleWithParam(String name) {
        return "simpleWithParam: 当出现异常时就会降级调用这里的方法!";
    }

    @Override
    public String simpleWithBody(Integer[] ids) {
        return "simpleWithBody: 当出现异常时就会降级调用这里的方法!";
    }

    @Override
    public String simpleWithHeader(String name) {
        return "simpleWithHeader: 当出现异常时就会降级调用这里的方法!";
    }
}
