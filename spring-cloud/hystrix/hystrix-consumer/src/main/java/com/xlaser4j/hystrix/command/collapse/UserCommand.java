package com.xlaser4j.hystrix.command.collapse;

import java.util.List;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.xlaser4j.hystrix.model.User;

/**
 * @package: com.xlaser4j.hystrix.command.collapse
 * @author: Elijah.D
 * @time: 2020/3/25 15:39
 * @description:
 * @modified: Elijah.D
 */
public class UserCommand extends HystrixCommand<List<User>> {
    private List<Integer> ids;

    private UserServiceWithCommand service;

    /**
     * 构造函数
     * <p>
     * super(setter),可以传递参数setter,然后new UserCommand()的时候再传递这个setter属性,也可以这里直接设置
     *
     * @param ids     参数
     * @param service service
     */
    public UserCommand(List<Integer> ids, UserServiceWithCommand service) {
        // 如果setter一致,可以不用添加参数,直接这里赋值设置属性即可
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("userGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("userKey")));
        this.ids = ids;
        this.service = service;
    }

    @Override
    protected List<User> run() {
        return service.listUsers(ids);
    }
}
