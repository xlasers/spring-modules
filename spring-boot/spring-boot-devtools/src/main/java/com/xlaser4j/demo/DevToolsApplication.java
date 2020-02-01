package com.xlaser4j.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * By default, any open project in your IDE will be loaded using the “restart” classloader, and any
 * regular .jar file will be loaded using the “base” classloader.
 *
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/0/0 00:00
 * @description: Start App Template
 * @modified: Elijah.D
 */
@SpringBootApplication
public class DevToolsApplication {
    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");

        System.out.println("\n==============================devtools是利用两种不同的classloader实现热部署功能:base classloader & restart classloader.==============================\n");
        System.out.println("\n==============================1.base classloader: 仅仅加载引入的依赖,如spring相关依赖,这些依赖的java文件是只读文件,在项目中不可以变更.==============================\n");
        System.out.println("\n==============================2.restart classloader:仅仅项目中自己的java文件,这些文件会随着coding不停的变更.==============================\n");
        System.out.println("\n==============================devtools是当classpath下的类文件(静态资源参考application.yml配置/以及浏览器插件livereload)变更时重新加载restart classloader,而重新启动项目还需要加载base classloader,因此速度更快.==============================\n");
        System.out.println("\n==============================IDEA默认是启动项目前编译类文件,所以要想实现devtools的自动部署,需要额外设置idea自动编译(Google一下),也可以Ctrl+F9强制编译.==============================\n");
        System.out.println("\n==============================application.yml文件可以配置手动触发的文件,当restart资源变更时,手动修改对应的file即可重启==============================\n");
        System.out.println("\n==============================Maven多模块项目中凡是带有devtools依赖的可以在本机当前用户目录下建立.spring-boot-devtools.properties文件统一全局配置.==============================\n");
        System.out.println("\n==============================???本项目demo或许时Maven多模块嵌套层数过度可能失效???==============================\n");
        SpringApplication.run(DevToolsApplication.class, args);
    }
}