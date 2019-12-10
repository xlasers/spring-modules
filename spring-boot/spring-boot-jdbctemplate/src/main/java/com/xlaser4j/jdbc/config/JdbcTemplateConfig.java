package com.xlaser4j.jdbc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @package: com.xlaser4j.jdbc.config
 * @author: Elijah.D
 * @time: 2019/12/10 14:18
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@Configuration
public class JdbcTemplateConfig {
    @Bean
    JdbcTemplate jdbcTemplateRead(@Qualifier("readDataSource") DataSource read) {
        return new JdbcTemplate(read);
    }

    @Bean
    JdbcTemplate jdbcTemplateWrite(@Qualifier("writeDataSource") DataSource write) {
        return new JdbcTemplate(write);
    }
}
