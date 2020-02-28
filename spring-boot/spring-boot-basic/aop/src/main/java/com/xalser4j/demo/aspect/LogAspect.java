package com.xalser4j.demo.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @package: com.xalser4j.demo.aspect
 * @author: Elijah.D
 * @time: 2019/12/14 14:40
 * @description:
 * @modified: Elijah.D
 */
@Aspect
@Component
public class LogAspect {
    /**
     * 标识符	                      含义
     * execution()	                  表达式的主体
     * 第一个“*”符号	                  任意返回值类型
     * com.xalser4j.aop.dao     	  AOP所切的服务包名,即,需要进行横切的业务类
     * 包名后面的“..”	              当前包及子包
     * 第二个“*”	                      表示类名,*即所有类
     * .*(..)	                      任何方法名,括号表示参数,两个点表示任何参数类型
     */
    @Pointcut("execution(* com.xalser4j.demo.dao..*.*(..))")
    public void point() {
    }

    /**
     * 前置通知,在目标方法的执行之前执行,即在连接点之前进行执行
     *
     * @param joinPoint
     */
    @Before(value = "point()")
    public void before(JoinPoint joinPoint) {
        System.out.println("before: " + joinPoint.getSignature().getName());
    }

    /**
     * 后置通知,在连接点方法完成之后执行,无论连接点方法执行成功还是出现异常,都将执行后置方法
     *
     * @param joinPoint
     */
    @After(value = "point()")
    public void after(JoinPoint joinPoint) {
        System.out.println("after: " + joinPoint.getSignature().getName());
    }

    /**
     * 返回通知,当连接点方法成功执行后,返回通知方法才会执行,如果连接点方法出现异常,则返回通知方法不执行
     * 返回通知方法在目标方法执行成功后才会执行,所以,返回通知方法可以拿到目标方法(连接点方法)执行后的结果
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "point()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("afterReturning: " + joinPoint.getSignature().getName() + ": " + result + "\n\n");
    }

    /**
     * 异常通知,只在连接点方法出现异常后才会执行,否则不执行.在异常通知方法中可以获取连接点方法出现的异常
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "point()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        System.out.println("afterThrowing: " + joinPoint.getSignature().getName() + ": " + e.getMessage());
    }

    /**
     * 环绕通知,环绕通知方法可以包含上面四种通知方法,环绕通知的功能最全面.环绕通知需要携带ProceedingJoinPoint
     * 类型的参数,且环绕通知必须有返回值,返回值即为目标方法的返回值
     *
     * @param joinPoint
     * @return
     */
    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) {
        // result为连接点的放回结果
        Object result;
        String methodName = joinPoint.getSignature().getName();

        // 前置通知方法
        System.out.println("around:前置通知方法>目标方法名：" + methodName + ",参数为：" + Arrays.asList(joinPoint.getArgs()));

        // 执行目标方法
        try {
            result = joinPoint.proceed();

            // 返回通知方法
            System.out.println("around:返回通知方法>目标方法名" + methodName + ",实际返回值：" + result);
        } catch (Throwable e) {
            // 异常通知方法
            System.out.println("around:异常通知方法>目标方法名" + methodName + ",异常为：" + e);
        }

        // 后置通知
        System.out.println("around:后置通知方法>目标方法名" + methodName);

        return "强制返回值: 强制返回自定义返回值,那么无论实际proceed是多少,都会返回这个设置的字符串内容!";
    }
}
