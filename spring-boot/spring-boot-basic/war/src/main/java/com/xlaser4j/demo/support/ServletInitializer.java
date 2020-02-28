package com.xlaser4j.demo.support;

import com.xlaser4j.demo.WarApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

/**
 * @package: com.xlaser4j.demo.support
 * @author: Elijah.D
 * @time: 2020/2/7 14:11
 * @description: war包生成
 * @modified: Elijah.D
 */
public class ServletInitializer extends SpringBootServletInitializer {
    /**
     * Note that a WebApplicationInitializer is only needed if you are building a war file and
     * deploying it. If you prefer to run an embedded web server then you won't need this at all.
     *
     * @param application
     * @return
     * @see WebApplicationInitializer
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WarApplication.class);
    }
}
