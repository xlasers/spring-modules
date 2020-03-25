package com.xlaser4j.hystrix.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.xlaser4j.hystrix.annotation.AnnotationService;
import com.xlaser4j.hystrix.annotation.collapse.UserServiceWithAnnotation;
import com.xlaser4j.hystrix.command.TestSimpleCommand;
import com.xlaser4j.hystrix.command.collapse.UserCollapser;
import com.xlaser4j.hystrix.command.collapse.UserServiceWithCommand;
import com.xlaser4j.hystrix.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @package: com.xlaser4j.hystrix.controller
 * @author: Elijah.D
 * @time: 2020 /3/25 12:51
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class ConsumerController {
    private final RestTemplate template;

    private final AnnotationService annotationService;

    private final UserServiceWithCommand userServiceWithCommand;

    private final UserServiceWithAnnotation userServiceWithAnnotation;

    public ConsumerController(AnnotationService annotationService, RestTemplate template, UserServiceWithCommand userServiceWithCommand, UserServiceWithAnnotation userServiceWithAnnotation) {
        this.annotationService = annotationService;
        this.template = template;
        this.userServiceWithCommand = userServiceWithCommand;
        this.userServiceWithAnnotation = userServiceWithAnnotation;
    }

    /**
     * annotation: synchronize-同步调用
     * <p>
     * 手动关闭生产端,查看fallback调用
     *
     * @return the string
     */
    @GetMapping("/as")
    public String testByAnnotationWithSync() {
        return annotationService.sync();
    }

    /**
     * annotation: asynchronize-异步调用
     * <p>
     * 手动关闭生产端,查看fallback调用
     *
     * @return the string
     */
    @GetMapping("/aa")
    public String testByAnnotationWithAsync() throws ExecutionException, InterruptedException {
        return annotationService.async().get();
    }

    /**
     * annotation: error-消费端异常
     *
     * @return the string
     */
    @GetMapping("/ae")
    public String testByAnnotationWithError() {
        return annotationService.error();
    }

    /**
     * annotation: 缓存
     * <p>
     * 缓存需要一个生命周期,从初始化{@link HystrixRequestContext#initializeContext()}开始,到关闭{@link HystrixRequestContext#close()}结束
     */
    @GetMapping("/ac")
    public void testByAnnotationWithCache() {
        System.out.println("\n==========================================================================");
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        System.out.println("第一次调用: " + annotationService.cache("param11", "param22"));
        System.out.println("第二次调用,缓存有效: " + annotationService.cache("param11", "param22"));
        System.out.println("第三次调用,不同参数,缓存无效: " + annotationService.cache("param1122", "param22"));
        context.close();
        try {
            System.out.println("第四次调用,close之后,缓存无效: " + annotationService.cache("param11", "param22"));
        } catch (Exception e) {
            System.out.println("\n带有缓存的注解,必须在缓存周期中使用,close之后使用则抛出异常: IllegalStateException: Request caching is not available. Maybe you need to initialize the HystrixRequestContext?");
        }
        System.out.println("==========================================================================\n");
    }

    /**
     * annotation: 缓存指定key
     */
    @GetMapping("/ak")
    public void testByAnnotationWithCacheKey() {
        System.out.println("\n==========================================================================");
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        System.out.println("第一次调用: " + annotationService.cacheWithKey("param11", "param22"));

        System.out.println("第二次调用,改变param2,缓存有效: " + annotationService.cacheWithKey("param11", "param2233"));
        System.out.println("第三次调用,改变param1,缓存无效: " + annotationService.cacheWithKey("param1122", "param22"));

        context.close();
        System.out.println("==========================================================================\n");
    }

    /**
     * annotation: 缓存清除
     */
    @GetMapping("/ar")
    public void testByAnnotationWithCacheRemove() {
        System.out.println("\n==========================================================================");
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        System.out.println("第一次调用: " + annotationService.cache("param11", "param22"));

        System.out.println("清除缓存,改变参数,清除无效: " + annotationService.cacheRemove("param1122", "param22"));
        System.out.println("第二次调用,使用缓存: " + annotationService.cache("param11", "param22"));

        System.out.println("清除缓存,参数一致,清除有效: " + annotationService.cacheRemove("param11", "param22"));
        System.out.println("再次调用,缓存失效: " + annotationService.cache("param11", "param22"));

        context.close();
        System.out.println("==========================================================================\n");
    }

    /**
     * command: synchronize & asynchronize
     * <p>
     * 手动关闭生产端,查看fallback调用: 注意一个command只能执行一次
     *
     * @return the string
     */
    @GetMapping("/c")
    public String testByCommand() throws ExecutionException, InterruptedException {
        HystrixCommand.Setter groupKey = HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("test"));

        // 直接执行
        TestSimpleCommand executeCommand = new TestSimpleCommand(groupKey, template);
        String execute = executeCommand.execute();
        System.out.println("\n===========> execute: " + execute);

        // 异步执行,然后get结果
        TestSimpleCommand futureCommand = new TestSimpleCommand(groupKey, template);
        Future<String> queue = futureCommand.queue();
        String future = queue.get();
        System.out.println("\n===========> future: " + future);

        return execute + " - " + future;
    }

    /**
     * command: 演示请求合并情况
     * <p>
     * 注意,请求合并仍然需要提供对应的context环境
     */
    @GetMapping("/ag")
    public void testByAnnotationWithCollapse() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        // 查看provider控制台请求调用两次
        Future<User> f1 = userServiceWithAnnotation.getUserById(1);
        Future<User> f2 = userServiceWithAnnotation.getUserById(2);
        Future<User> f3 = userServiceWithAnnotation.getUserById(3);

        // 这里睡眠600,保证hystrix不会合并这一个请求
        Thread.sleep(600);
        Future<User> f4 = userServiceWithAnnotation.getUserById(4);

        User u1 = f1.get();
        User u2 = f2.get();
        User u3 = f3.get();
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);

        User u4 = f4.get();
        System.out.println(u4);

        context.close();
    }

    /**
     * command: 演示请求合并情况
     * <p>
     * 注意,请求合并仍然需要提供对应的context环境
     */
    @GetMapping("/cg")
    public void testByCommandWithCollapse() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        // 查看provider控制台请求调用两次
        UserCollapser cmd1 = new UserCollapser(1, userServiceWithCommand);
        Future<User> f1 = cmd1.queue();
        UserCollapser cmd2 = new UserCollapser(2, userServiceWithCommand);
        Future<User> f2 = cmd2.queue();

        // 这里睡眠600,保证hystrix不会合并这两个请求,但是后两个仍然合并
        UserCollapser cmd4 = new UserCollapser(4, userServiceWithCommand);
        UserCollapser cmd3 = new UserCollapser(3, userServiceWithCommand);
        Thread.sleep(600);
        Future<User> f4 = cmd4.queue();
        Future<User> f3 = cmd3.queue();

        User u1 = f1.get();
        User u2 = f2.get();
        System.out.println(u1);
        System.out.println(u2);

        User u3 = f3.get();
        User u4 = f4.get();
        System.out.println(u3);
        System.out.println(u4);

        context.close();
    }
}
