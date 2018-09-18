package com.xlasers.spring.aop;

import java.lang.reflect.Method;

import com.xlasers.spring.annotation.DataSource;
import com.xlasers.spring.config.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import static com.xlasers.spring.constant.DataSourceNames.FIRST;

/**
 * The type Data source aspect.
 *
 * @package: com.xlasers.spring.aop
 * @author: Elijah.D
 * @time: CreateAt 2018/10/4 && 10:05
 * @description: 多数据源 ，切面处理类
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Slf4j
@Aspect
@Component
public class DataSourceAspect implements Ordered {
	/**
	 * Data source point.
	 *
	 * <p>切点是带有注解@DataSource,表示拦截含有这个注解的方法
	 */
	@Pointcut("@annotation(com.xlasers.spring.annotation.DataSource)")
	public void dataSourcePoint() {
		// 表示拦截含有这个注解的方法
	}

	/**
	 * Around object.
	 *
	 * <p>切入带有@DataSource注解的方法
	 *
	 * @param point the point
	 * @return the object
	 * @throws Throwable the throwable
	 */
	@Around("dataSourcePoint()")
	public Object around(ProceedingJoinPoint point) throws Throwable {

		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();

		DataSource source = method.getAnnotation(DataSource.class);
		if (source == null) {
			DynamicDataSource.setDataSource(FIRST);
			log.info("【DynamicDataSource】 set dataSource, name: {}", FIRST);
		} else {
			DynamicDataSource.setDataSource(source.name());
			log.info("【DynamicDataSource】 set dataSource, name: {}", source.name());
		}

		try {
			return point.proceed();
		} finally {
			DynamicDataSource.clearDataSource();
			log.info("【DynamicDataSource】 Clean the dataSource !");
		}
	}

	/**
	 * 数字越小优先级越高
	 *
	 * @return
	 */
	@Override
	public int getOrder() {
		return 1;
	}
}
