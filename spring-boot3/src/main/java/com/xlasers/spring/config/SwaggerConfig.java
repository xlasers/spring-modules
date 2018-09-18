package com.xlasers.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type Swagger config.
 *
 * @package: com.xlasers.spring.config
 * @author: Elijah.D
 * @time: CreateAt 2018/9/29 && 14:03
 * @description: 配置swagger类
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * get apiInfo
     *
     * @return
     */
    private static ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("api docs")
                .description("xlasers's api domain")
                .termsOfServiceUrl("暂时为空")
                .version("V1.0")
                .build();
    }

    /**
     * Rest template rest template.
     *
     * @return the rest template
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Docket docket.
     *
     * @return the docket
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xlasers.spring"))
                .paths(PathSelectors.any())
                .build();
    }
}
