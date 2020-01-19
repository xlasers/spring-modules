package com.xlaser4j.staticweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * {@link org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration}getResourceLocations().
 * <p>
 * spring-boot的静态资源处理,默认5个路径依次是: {@link ResourceProperties#getStaticLocations()}
 * "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"
 * <p>
 * 最后一个是mvc开发下的webapp目录:SERVLET_LOCATIONS = { "/" },不建议创建使用
 * <p>
 * ResourceProperties的注解@ConfigurationProperties(prefix = "spring.resources"),因此可以通过application配置
 * staticLocations属性是实现自定义静态资源的路径,比如额外添加一个custom-by-yml
 * <p>
 * 也可以通过重写{@link org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addResourceHandlers(ResourceHandlerRegistry)}
 * 方法,实现静态资源路径的重新定义,eg:额外添加一个custom-by-config
 *
 * @package: com.xlaser4j.staticweb
 * @author: Elijah.D
 * @time: 2020/0/0 00:00
 * @description: Start App Template
 * @modified: Elijah.D
 */
@SpringBootApplication
public class StaticApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================springBoot静态资源访问的有序目录一共5个:WebMvcAutoConfiguration#getResourceLocations,默认是static==============================\n");
        System.out.println("\n==============================通过配置application文件,可以手动定义静态资源的路径==============================\n");
        System.out.println("\n==============================通过java配置类重写addResourcesHandlers方法,可以手动定义静态资源的路径==============================\n");
        SpringApplication.run(StaticApplication.class, args);
    }
}
