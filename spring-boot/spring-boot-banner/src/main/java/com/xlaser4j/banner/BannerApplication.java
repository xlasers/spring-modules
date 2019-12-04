package com.xlaser4j.banner;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Sets the mode used to display the banner when the application runs. Defaults to {@code Banner.Mode.CONSOLE}.
 * <p>
 * OFF CONSOLE LOG {@link org.springframework.boot.Banner.Mode}
 *
 * @package: com.xlaser4j.banner
 * @author: Elijah.D
 * @time: 2019/12/4 16:26
 * @description: 测试banner开关
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@SpringBootApplication
public class BannerApplication {
    public static void main(String[] args) {
        /// SpringApplication application = new SpringApplication(BannerApplication.class);
        SpringApplicationBuilder builder = new SpringApplicationBuilder(BannerApplication.class);
        SpringApplication application = builder.build();

        // RESOURCE下自定义banner.txt,app设置三种可选模式,默认打印控制台
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
