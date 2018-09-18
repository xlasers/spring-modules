package com.xlasers.spring.config;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * The type Dynamic data source.
 *
 * <p>
 * 动态数据源
 *
 * @package: com.xlasers.spring.config
 * @author: Elijah.D
 * @time: CreateAt 2018/10/3 && 23:16
 * @description: 动态数据源
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	/**
	 * The constant CONTEXT_HOLDER.
	 *
	 * <p>ThreadLocal用于提供线程局部变量，在多线程环境可以保证各个线程里的变量独立于其它线程里的变量。
	 *    为每个线程创建一个【单独的变量副本】相当于线程的 private static类型变量。
	 */
	private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

	/**
	 * Instantiates a new Dynamic data source.
	 *
	 * <p>决定使用哪个数据源之前需要把多个数据源的信息以及默认数据源信息配置好
	 *
	 * @param defaultTargetDataSource the default target data source
	 * @param targetDataSources       the target data sources
	 */
	public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
		super.setDefaultTargetDataSource(defaultTargetDataSource);
		super.setTargetDataSources(targetDataSources);
		//配置bean属性,配置完所有bean属性之后调用,如果配置错误,抛出异常
		super.afterPropertiesSet();
	}

	/**
	 * Gets data source.
	 *
	 * @return the data source
	 */
	public static String getDataSource() {
		return CONTEXT_HOLDER.get();
	}

	/**
	 * Sets data source.
	 *
	 * @param dataSource the data source
	 */
	public static void setDataSource(String dataSource) {
		CONTEXT_HOLDER.set(dataSource);
	}

	/**
	 * Clear data source.
	 */
	public static void clearDataSource() {
		CONTEXT_HOLDER.remove();
	}

	/**
	 * 决定使用哪个数据源
	 *
	 * @return
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return getDataSource();
	}
}
