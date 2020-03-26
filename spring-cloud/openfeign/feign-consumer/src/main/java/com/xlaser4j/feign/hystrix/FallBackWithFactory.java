package com.xlaser4j.feign.hystrix;

import com.xlaser4j.feign.remote.ExtendProviderRemote;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @package: com.xlaser4j.feign.hystrix
 * @author: Elijah.D
 * @time: 2020/3/26 14:50
 * @description: 服务降级2: 利用工厂类实现
 * @modified: Elijah.D
 */
@Component
@SuppressWarnings("SpringMVCViewInspection")
public class FallBackWithFactory implements FallbackFactory<ExtendProviderRemote> {
    @Override
    public ExtendProviderRemote create(Throwable throwable) {
        return new ExtendProviderRemote() {
            @Override
            public String commonGet() {
                return "commonGet: 当出现异常时就会降级调用这里的方法!";
            }

            @Override
            public String commonGetWithParam(String key) {
                return "commonGetWithParam: 当出现异常时就会降级调用这里的方法!";
            }

            @Override
            public String commonGetWithBody(String[] keys) {
                return "commonGetWithBody: 当出现异常时就会降级调用这里的方法!";
            }
        };
    }
}
