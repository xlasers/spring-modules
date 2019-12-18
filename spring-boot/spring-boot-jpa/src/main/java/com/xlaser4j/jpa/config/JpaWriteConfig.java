package com.xlaser4j.jpa.config;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @package: com.xlaser4j.jpa.config
 * @author: Elijah.D
 * @time: 2019/12/18 17:22
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.xlaser4j.jpa.dao.write", entityManagerFactoryRef = "localContainerEntityManagerFactoryBeanOfWrite", transactionManagerRef = "platformTransactionManagerOfWrite")
public class JpaWriteConfig {
    @Autowired
    JpaProperties prop;

    @Autowired
    @Qualifier("writeDataSource")
    DataSource writeDataSource;

    /**
     * transaction
     *
     * @param builder
     * @return
     */
    @Bean
    PlatformTransactionManager platformTransactionManagerOfWrite(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(localContainerEntityManagerFactoryBeanOfWrite(builder).getObject()));
    }

    /**
     * entityManagerFactory
     *
     * @param builder
     * @return
     */
    @Bean
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBeanOfWrite(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(writeDataSource)
                .properties(prop.getProperties())
                .persistenceUnit("write")
                .packages("com.xlaser4j.jpa.entity")
                .build();
    }
}
