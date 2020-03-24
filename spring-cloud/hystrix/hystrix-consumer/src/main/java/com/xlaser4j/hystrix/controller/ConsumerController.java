package com.xlaser4j.hystrix.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.xlaser4j.hystrix.annotation.AnnotationService;
import com.xlaser4j.hystrix.command.TestSimpleCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * The type Test controller.
 *
 * @package: com.xlaser4j.hystrix.controller
 * @author: Elijah.D
 * @time: 2020 /3/25 12:51
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class ConsumerController {
    private final RestTemplate template;

    private final AnnotationService service;

    public ConsumerController(AnnotationService service, RestTemplate template) {
        this.service = service;
        this.template = template;
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
        return service.sync();
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
        return service.async().get();
    }

    /**
     * annotation: error-消费端异常
     *
     * @return the string
     */
    @GetMapping("/ae")
    public String testByAnnotationWithError() {
        return service.error();
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
        System.out.println("第一次调用: " + service.cache("param11", "param22"));
        System.out.println("第二次调用,缓存有效: " + service.cache("param11", "param22"));
        System.out.println("第三次调用,不同参数,缓存无效: " + service.cache("param1122", "param22"));
        context.close();
        try {
            System.out.println("第四次调用,close之后,缓存无效: " + service.cache("param11", "param22"));
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
        System.out.println("第一次调用: " + service.cacheWithKey("param11", "param22"));

        System.out.println("第二次调用,改变param2,缓存有效: " + service.cacheWithKey("param11", "param2233"));
        System.out.println("第三次调用,改变param1,缓存无效: " + service.cacheWithKey("param1122", "param22"));

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
        System.out.println("第一次调用: " + service.cache("param11", "param22"));

        System.out.println("清除缓存,改变参数,清除无效: " + service.cacheRemove("param1122", "param22"));
        System.out.println("第二次调用,使用缓存: " + service.cache("param11", "param22"));

        System.out.println("清除缓存,参数一致,清除有效: " + service.cacheRemove("param11", "param22"));
        System.out.println("再次调用,缓存失效: " + service.cache("param11", "param22"));

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
}
