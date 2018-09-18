package com.xlasers.spring;

import com.xlasers.spring.config.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * The type Data source application.
 *
 * <p>排除springboot自动配置数据源,import手动配置{@link DynamicDataSourceConfig},
 *
 * @package: com.xlasers.spring
 * @author: Elijah.D
 * @time: CreateAt 2018/10/2 && 20:36
 * @description: springBoot主类
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.xlasers.spring.mapper")
@Import({DynamicDataSourceConfig.class})
public class DataSourceApplication {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        SpringApplication.run(DataSourceApplication.class);
    }
}
