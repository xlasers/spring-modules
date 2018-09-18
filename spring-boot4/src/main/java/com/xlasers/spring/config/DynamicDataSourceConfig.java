package com.xlasers.spring.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static com.xlasers.spring.constant.DataSourceNames.FIRST;
import static com.xlasers.spring.constant.DataSourceNames.SECOND;

/**
 * The type Dynamic data source config.
 *
 * @package: com.xlasers.spring.config
 * @author: Elijah.D
 * @time: CreateAt 2018/10/3 && 11:34
 * @description: 配置多数据源
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Configuration
public class DynamicDataSourceConfig {
    /**
     * First data source data source.
     *
     * @return the data source
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.first")
    public DataSource firstDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * Second data source data source.
     *
     * @return the data source
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.second")
    public DataSource secondDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * Data source dynamic data source.
     *
     * @param firstDataSource  the first data source
     * @param secondDataSource the second data source
     * @return the dynamic data source
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource firstDataSource, DataSource secondDataSource) {

        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(FIRST, firstDataSource);
        targetDataSources.put(SECOND, secondDataSource);

        return new DynamicDataSource(firstDataSource, targetDataSources);
    }
}
