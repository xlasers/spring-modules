package com.xlaser4j.mybatis.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @package: com.xlaser4j.mybatis.config
 * @author: Elijah.D
 * @time: 2019/12/14 15:31
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@Configuration
@MapperScan(basePackages = "com.xlaser4j.mybatis.mapper.write", sqlSessionFactoryRef = "writeSessionFactory", sqlSessionTemplateRef = "writeSessionTemplate")
public class MybatisWriteConfig {
    @Resource(name = "writeDataSource")
    DataSource source;

    /**
     * build factory
     *
     * @return
     * @throws Exception
     */
    @Bean
    SqlSessionFactory writeSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(source);
        return bean.getObject();
    }

    /**
     * get template
     *
     * @return
     * @throws Exception
     */
    @Bean
    SqlSessionTemplate writeSessionTemplate() throws Exception {
        return new SqlSessionTemplate(writeSessionFactory());
    }
}
