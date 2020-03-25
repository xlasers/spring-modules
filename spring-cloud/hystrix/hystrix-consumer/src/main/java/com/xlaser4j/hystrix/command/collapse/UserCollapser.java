package com.xlaser4j.hystrix.command.collapse;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import com.xlaser4j.hystrix.model.User;

/**
 * @package: com.xlaser4j.hystrix.command.collapse
 * @author: Elijah.D
 * @time: 2020 /3/25 15:48
 * @description: 通过请求命令实现, 请求合并的方法, 较为复杂
 * @modified: Elijah.D
 */
public class UserCollapser extends HystrixCollapser<List<User>, User, Integer> {
    private Integer id;

    private UserServiceWithCommand service;

    /**
     * 构造函数
     * <p>
     * withTimerDelayInMilliseconds: 设置两个请求合并的间隔时间为500ms,超过则不再等待,直接请求服务
     *
     * @param id      the id
     * @param service the service
     */
    public UserCollapser(Integer id, UserServiceWithCommand service) {
        super(Setter
                .withCollapserKey(HystrixCollapserKey.Factory.asKey("userCollapser"))
                .andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(500)));
        this.id = id;
        this.service = service;
    }

    /**
     * 请求参数,原方法可以接受的参数,单次请求是一个
     *
     * @return id
     */
    @Override
    public Integer getRequestArgument() {
        return id;
    }

    /**
     * 请求合并的方法
     *
     * @param collapsedReqs 合并
     * @return data
     */
    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, Integer>> collapsedReqs) {
        List<Integer> ids = collapsedReqs.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList());
        return new UserCommand(ids, service);
    }

    /**
     * 请求结果分发的结果
     *
     * @param batchRes      users
     * @param collapsedReqs data
     */
    @Override
    protected void mapResponseToRequests(List<User> batchRes, Collection<CollapsedRequest<User, Integer>> collapsedReqs) {
        int count = 0;
        for (CollapsedRequest<User, Integer> req : collapsedReqs) {
            req.setResponse(batchRes.get(count++));
        }
    }
}
