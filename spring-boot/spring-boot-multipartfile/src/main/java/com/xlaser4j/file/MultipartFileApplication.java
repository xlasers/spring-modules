package com.xlaser4j.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.multipart.MultipartResolver;

/**
 * {@link org.springframework.web.multipart.MultipartResolver}
 * MultipartFile解析需要相应的resolver,servlet3.0前后有两种实现.
 * <p>
 * CommonsMultipartResolver:需要依赖 Commons-FileUpload
 * Servlet-based {@link MultipartResolver} implementation for Apache Commons FileUpload</a> 1.2 or above.
 * <p>
 * StandardServletMultipartResolver:
 * Standard implementation of the {@link MultipartResolver} interface, based on the Servlet 3.0
 * {@link javax.servlet.http.Part} API. To be added as "multipartResolver" bean to a Spring
 * DispatcherServlet context, without any extra configuration at the bean level.
 *
 * @package: com.xlaser4j.file
 * @author: Elijah.D
 * @time: 2020/0/0 00:00
 * @description: Start App Template
 * @modified: Elijah.D
 */
@SpringBootApplication
public class MultipartFileApplication {
    public static void main(String[] args) {
        System.out.println("\n==============================Servlet3.0之前上传文件需要用到额外的依赖commons-fileupload满足CommonsMultipartResolver==============================\n");
        System.out.println("\n==============================Servlet3.0之后上传文件可以使用StandardServletMultipartResolver,因此springboot项目中可以不用commons依赖==============================\n");
        SpringApplication.run(MultipartFileApplication.class, args);
    }
}
