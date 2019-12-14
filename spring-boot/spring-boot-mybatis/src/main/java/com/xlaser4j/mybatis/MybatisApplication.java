package com.xlaser4j.mybatis;

import javax.annotation.PostConstruct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 这里不需要添加{@link MapperScan#basePackages()}扫描mapper类,因为config中已经添加配置.
 * <p>
 * 此外,也可以直接在mapper类中直接添加注解{@link org.apache.ibatis.annotations.Mapper}实现扫描.
 * <p>
 * 这里演示多数据源,都是通过config类配置,实际业务中一半通过中间件实现mybatis的多数据源配置.
 *
 * @package: com.xlaser4j.mybatis
 * @author: Elijah.D
 * @time: 2019/12/14 15:26
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@SpringBootApplication
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

    @PostConstruct
    public void testMybatis() {
    }
}
