package com.xlaser4j.feign.remote;

import com.xlaser4j.feign.api.ICommonService;
import com.xlaser4j.feign.hystrix.FallBackWithFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @package: com.xlaser4j.feign.remote
 * @author: Elijah.D
 * @time: 2020/3/26 14:30
 * @description: 通过feign的继承特性实现统一管理定义接口
 * @modified: Elijah.D
 */
@FeignClient(value = "temp-feign-provider-temp", fallbackFactory = FallBackWithFactory.class)
public interface ExtendProviderRemote extends ICommonService {

    /// 请分开测试simple和Extend,相同的feignClient只能存在一个: temp-feign-provider-temp ===>> feign-provider
}
