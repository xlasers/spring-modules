package com.xlaser4j.autoconfigure;

import com.xlaser4j.autoconfigure.annotation.ActiveMode;
import com.xlaser4j.autoconfigure.annotation.EnableFirstSelector;
import com.xlaser4j.autoconfigure.annotation.EnableImport;
import com.xlaser4j.autoconfigure.annotation.EnableSecondSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CachingConfigurationSelector;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 测试spring-boot自动装配3种情况
 * <p>
 * 1.通过enable手动开启扫描注入
 * eg:{@link EnableWebMvc},通过{@linkplain Import 注解直接导入实现}.
 * eg:{@link EnableCaching},通过{@linkplain CachingConfigurationSelector 条件选择器实现}.
 * <p>
 * 2.通过读取META-INF下配置文件注入
 * <p>
 * 3.测试conditional根据条件注入
 *
 * @package: com.xlaser4j.autoconfigure
 * @author: Elijah.D
 * @time: 2019/10/26 15:59
 * @description: run app
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@EnableImport
@EnableFirstSelector
@EnableSecondSelector(mode = ActiveMode.SECOND)
@SpringBootApplication
public class AutoConfigureApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutoConfigureApplication.class, args);
    }
}
